package aoc

import cats.data.NonEmptyList
import cats.effect.unsafe.implicits.global
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class DayOneSpec extends AnyFunSuite with Matchers {

  test("Part 1 spec") {
    val input =
      """1000
        |2000
        |3000
        |
        |4000
        |
        |5000
        |6000
        |
        |7000
        |8000
        |9000
        |
        |10000
        |""".stripMargin

    val loaded = DayOne.load(input).unsafeRunSync()

    loaded shouldBe NonEmptyList.of(
      NonEmptyList.of(1000, 2000, 3000),
      NonEmptyList.of(4000),
      NonEmptyList.of(5000, 6000),
      NonEmptyList.of(7000, 8000, 9000),
      NonEmptyList.of(10000)
    )

    DayOne.elfWithTheMostCalories(loaded) shouldBe 24000
  }

  test("Part 2 spec") {
    val input =
      """1000
        |2000
        |3000
        |
        |4000
        |
        |5000
        |6000
        |
        |7000
        |8000
        |9000
        |
        |10000
        |""".stripMargin

    val loaded = DayOne.load(input).unsafeRunSync()

    loaded shouldBe NonEmptyList.of(
      NonEmptyList.of(1000, 2000, 3000),
      NonEmptyList.of(4000),
      NonEmptyList.of(5000, 6000),
      NonEmptyList.of(7000, 8000, 9000),
      NonEmptyList.of(10000)
    )

    DayOne.elvesWithTheMostCalories(loaded) shouldBe 45000
  }
}
