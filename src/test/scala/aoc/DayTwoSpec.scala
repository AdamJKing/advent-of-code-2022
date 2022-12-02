package aoc

import aoc.DayTwo.{Hand, Round}
import cats.data.NonEmptyList
import cats.effect.unsafe.implicits.global
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class DayTwoSpec extends AnyFunSuite with Matchers {

  test("Day 2 Spec") {

    val input =
      """A Y
        |B X
        |C Z
        |""".stripMargin

    val loaded = DayTwo.load(input).unsafeRunSync()

    loaded shouldBe NonEmptyList.of(
      Round(Hand.Rock, Hand.Paper),
      Round(Hand.Paper, Hand.Rock),
      Round(Hand.Scissors, Hand.Scissors)
    )

    Round(Hand.Rock, Hand.Paper).score shouldBe 8
    DayTwo.totalScore(loaded) shouldBe 15
  }
}
