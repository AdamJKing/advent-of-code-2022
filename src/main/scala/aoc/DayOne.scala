package aoc

import atto._
import Atto._
import cats.data.NonEmptyList
import cats.syntax.all._
import cats.effect.IO
import cats.kernel.Order

object DayOne {

  type Elf = NonEmptyList[BigInt]

  def load(input: String): IO[NonEmptyList[Elf]] = {
    val elf = many1(bigInt <~ char('\n'))
    val elves = elf sepBy1 char('\n')

    IO.fromEither {
      elves
        .parseOnly(input)
        .either
        .leftMap(err => new InputError(s"Failed to load elves: $err"))
    }
  }

  def elfWithTheMostCalories(elves: NonEmptyList[Elf]): BigInt =
    elves.map(_.sumAll).maximum

  def elvesWithTheMostCalories(elves: NonEmptyList[Elf]): BigInt =
    elves.map(_.sumAll).sorted(Order.reverse(Order[BigInt])).take(3).sumAll
}
