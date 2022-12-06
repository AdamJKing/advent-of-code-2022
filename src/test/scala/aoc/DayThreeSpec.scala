package aoc

import org.scalatest.funsuite.AnyFunSuite
import cats.effect.unsafe.implicits.global
import cats.data.NonEmptyList
import cats.syntax.all._
import org.scalatest.matchers.should.Matchers
import DayThree.Item

class DayThreeSpec extends AnyFunSuite with Matchers {

  test("Priority conversion") {
    Item('p').priority shouldBe 16
    Item('L').priority shouldBe 38
    Item('P').priority shouldBe 42
    Item('v').priority shouldBe 22
    Item('t').priority shouldBe 20
    Item('s').priority shouldBe 19
  }

  test("Day Three Part1 load Spec") {
    val input = """vJrwpWtwJgWrhcsFMMfFFhFp
                       |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                       |PmmdzqPrVvPwwTWBwg
                       |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                       |ttgJtRGJQctTZtZT
                       |CrZsJsPPZsGzwwsLwLmpwMDw""".stripMargin

    val loaded = DayThree.load(input).unsafeRunSync()

    loaded shouldBe NonEmptyList(
      (
        List(
          Item('v', 22),
          Item('J', 36),
          Item('r', 18),
          Item('w', 23),
          Item('p', 16),
          Item('W', 49),
          Item('t', 20),
          Item('w', 23),
          Item('J', 36),
          Item('g', 7),
          Item('W', 49),
          Item('r', 18)
        ),
        List(
          Item('h', 8),
          Item('c', 3),
          Item('s', 19),
          Item('F', 32),
          Item('M', 39),
          Item('M', 39),
          Item('f', 6),
          Item('F', 32),
          Item('F', 32),
          Item('h', 8),
          Item('F', 32),
          Item('p', 16)
        )
      ),
      List(
        (
          List(
            Item('j', 10),
            Item('q', 17),
            Item('H', 34),
            Item('R', 44),
            Item('N', 40),
            Item('q', 17),
            Item('R', 44),
            Item('j', 10),
            Item('q', 17),
            Item('z', 26),
            Item('j', 10),
            Item('G', 33),
            Item('D', 30),
            Item('L', 38),
            Item('G', 33),
            Item('L', 38)
          ),
          List(
            Item('r', 18),
            Item('s', 19),
            Item('F', 32),
            Item('M', 39),
            Item('f', 6),
            Item('F', 32),
            Item('Z', 52),
            Item('S', 45),
            Item('r', 18),
            Item('L', 38),
            Item('r', 18),
            Item('F', 32),
            Item('Z', 52),
            Item('s', 19),
            Item('S', 45),
            Item('L', 38)
          )
        ),
        (
          List(
            Item('P', 42),
            Item('m', 13),
            Item('m', 13),
            Item('d', 4),
            Item('z', 26),
            Item('q', 17),
            Item('P', 42),
            Item('r', 18),
            Item('V', 48)
          ),
          List(
            Item('v', 22),
            Item('P', 42),
            Item('w', 23),
            Item('w', 23),
            Item('T', 46),
            Item('W', 49),
            Item('B', 28),
            Item('w', 23),
            Item('g', 7)
          )
        ),
        (
          List(
            Item('w', 23),
            Item('M', 39),
            Item('q', 17),
            Item('v', 22),
            Item('L', 38),
            Item('M', 39),
            Item('Z', 52),
            Item('H', 34),
            Item('h', 8),
            Item('H', 34),
            Item('M', 39),
            Item('v', 22),
            Item('w', 23),
            Item('L', 38),
            Item('H', 34)
          ),
          List(
            Item('j', 10),
            Item('b', 2),
            Item('v', 22),
            Item('c', 3),
            Item('j', 10),
            Item('n', 14),
            Item('n', 14),
            Item('S', 45),
            Item('B', 28),
            Item('n', 14),
            Item('v', 22),
            Item('T', 46),
            Item('Q', 43),
            Item('F', 32),
            Item('n', 14)
          )
        ),
        (
          List(
            Item('t', 20),
            Item('t', 20),
            Item('g', 7),
            Item('J', 36),
            Item('t', 20),
            Item('R', 44),
            Item('G', 33),
            Item('J', 36)
          ),
          List(
            Item('Q', 43),
            Item('c', 3),
            Item('t', 20),
            Item('T', 46),
            Item('Z', 52),
            Item('t', 20),
            Item('Z', 52),
            Item('T', 46)
          )
        ),
        (
          List(
            Item('C', 29),
            Item('r', 18),
            Item('Z', 52),
            Item('s', 19),
            Item('J', 36),
            Item('s', 19),
            Item('P', 42),
            Item('P', 42),
            Item('Z', 52),
            Item('s', 19),
            Item('G', 33),
            Item('z', 26)
          ),
          List(
            Item('w', 23),
            Item('w', 23),
            Item('s', 19),
            Item('L', 38),
            Item('w', 23),
            Item('L', 38),
            Item('m', 13),
            Item('p', 16),
            Item('w', 23),
            Item('M', 39),
            Item('D', 30),
            Item('w', 23)
          )
        )
      )
    )

    loaded.map(DayThree.priorityOfCommon).sumAll shouldBe 157
  }

  test("Day2 Part2 Spec") {
    val input = """vJrwpWtwJgWrhcsFMMfFFhFp
                       |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                       |PmmdzqPrVvPwwTWBwg
                       |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                       |ttgJtRGJQctTZtZT
                       |CrZsJsPPZsGzwwsLwLmpwMDw""".stripMargin

    val loaded = DayThree.load(input).unsafeRunSync()
    DayThree.findBadges(loaded).sumAll shouldBe 70
  }
}
