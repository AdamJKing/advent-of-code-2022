package aoc

import atto._
import cats.effect._
import Atto._
import cats.syntax.all._
import cats.Monad
import cats.data.NonEmptyList

object DayThree {

  final case class Item(repr: Char, priority: Int)

  object Item {
    def apply(repr: Char): Item =
      Item(
        repr,
        // magic ASCII to priority converter
        if (repr.isUpper) repr.toInt - 38
        else repr.toInt - 96
      )

  }

  final case class Rucksack(a: Set[Item], b: Set[Item]) {
    val all = a ++ b
  }

  def priorityOfCommon(rucksack: Rucksack) = {
    val Rucksack(a, b) = rucksack
    a.find(b.contains).map(_.priority).getOrElse(0)
  }

  def findBadges(rucksacks: NonEmptyList[Rucksack]): Seq[Int] =
    rucksacks
      .grouped(3)
      .collect { case NonEmptyList(as, bs :: cs :: Nil) =>
        as.all
          .find { a => bs.all.contains(a) && cs.all.contains(a) }
          .fold(0)(_.priority)
      }
      .toSeq

  def load(input: String): IO[NonEmptyList[Rucksack]] = {
    val item     = (lower | upper).map(Item(_))
    val rucksack =
      many(item)
        .map(xs => xs.toSet.splitAt(xs.length / 2))
        .map { case (a, b) => Rucksack(a, b) }

    IO.fromEither {
      (rucksack sepBy1 oneOf("\r\n"))
        .parseOnly(input)
        .either
        .leftMap(err => new InputError(s"Failed to load rucksacks: $err"))
    }
  }
}
