package aoc

import cats.data.NonEmptyList
import cats.effect.IO
import cats.syntax.all._
import atto._
import Atto._
import aoc.DayTwo.Hand.defeatedBy

object DayTwo {

  sealed abstract class Hand(val score: Int)

  object Hand {
    case object Rock     extends Hand(score = 1)
    case object Paper    extends Hand(score = 2)
    case object Scissors extends Hand(score = 3)

    def defeatedBy(hand: Hand): Hand = hand match {
      case Rock     => Scissors
      case Paper    => Rock
      case Scissors => Paper
    }
  }

  final case class Round(opponent: Hand, suggested: Hand) {
    val score: Int = suggested.score + {
      if (opponent == suggested) 3
      else if (Hand.defeatedBy(suggested) == opponent) 6
      else 0
    }
  }

  def totalScore(rounds: NonEmptyList[Round]): Int = rounds.map(_.score).sumAll

  private val hand = {
    val rock     = oneOf("AX").as(Hand.Rock)
    val paper    = oneOf("BY").as(Hand.Paper)
    val scissors = oneOf("CZ").as(Hand.Scissors)

    rock | paper | scissors
  }

  object V1 {
    def load(input: String): IO[NonEmptyList[Round]] = {
      val round = (hand <~ spaceChar, hand).mapN(Round.apply)

      IO.fromEither {
        (round sepBy1 char('\n'))
          .parseOnly(input)
          .either
          .leftMap(err => new InputError(s"Failed to load rounds: $err"))
      }
    }
  }

  object V2 {

    sealed trait Outcome
    object Outcome {
      case object Lose extends Outcome
      case object Draw extends Outcome
      case object Win  extends Outcome
    }

    def decideHand(opponent: Hand, outcome: Outcome): Hand = outcome match {
      case Outcome.Lose => defeatedBy(opponent)
      case Outcome.Draw => opponent
      case Outcome.Win  =>
        Seq(Hand.Rock, Hand.Paper, Hand.Scissors)
          .find(defeatedBy(_) == opponent)
          .get
    }

    def load(input: String): IO[NonEmptyList[Round]] = {
      val outcome =
        (char('X') as Outcome.Lose) |
          (char('Y') as Outcome.Draw) |
          (char('Z') as Outcome.Win)

      val round = (hand <~ spaceChar, outcome).mapN {
        case (opponent, outcome) =>
          Round(opponent, decideHand(opponent, outcome))
      }

      IO.fromEither {
        (round sepBy1 char('\n'))
          .parseOnly(input)
          .either
          .leftMap(err => new InputError(s"Failed to load rounds: $err"))
      }
    }

    def totalScore(rounds: NonEmptyList[Round]): Int =
      rounds.map(_.score).sumAll
  }
}
