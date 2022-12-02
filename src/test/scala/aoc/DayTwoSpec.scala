package aoc

import aoc.DayTwo.V2.Outcome
import aoc.DayTwo.{Hand, Round, V1}
import cats.data.NonEmptyList
import cats.effect.unsafe.implicits.global
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class DayTwoSpec extends AnyFunSuite with Matchers {

  test("Day 2 Spec Part 1") {

    val input =
      """A Y
        |B X
        |C Z
        |""".stripMargin

    val loaded = DayTwo.V1.load(input).unsafeRunSync()

    loaded shouldBe NonEmptyList.of(
      Round(Hand.Rock, Hand.Paper),
      Round(Hand.Paper, Hand.Rock),
      Round(Hand.Scissors, Hand.Scissors)
    )

    Round(Hand.Rock, Hand.Paper).score shouldBe 8
    DayTwo.totalScore(loaded) shouldBe 15
  }

  test("hand choice") {
    DayTwo.V2.decideHand(Hand.Paper, Outcome.Win) shouldBe Hand.Scissors
    DayTwo.V2.decideHand(Hand.Paper, Outcome.Lose) shouldBe Hand.Rock
    DayTwo.V2.decideHand(Hand.Paper, Outcome.Draw) shouldBe Hand.Paper
  }

  test("Day 2 Spec Part 2") {
    val input =
      """A Y
        |B X
        |C Z
        |""".stripMargin

    val loaded = DayTwo.V2.load(input).unsafeRunSync()

    loaded shouldBe NonEmptyList.of(
      Round(Hand.Rock, Hand.Rock),
      Round(Hand.Paper, Hand.Rock),
      Round(Hand.Scissors, Hand.Rock)
    )

    DayTwo.totalScore(loaded) shouldBe 12
  }
}
