package aoc

import cats.effect.Resource
import cats.syntax.all._
import cats.effect.{IO, IOApp}

import scala.io.Source

object AdventOfCode2022 extends IOApp.Simple {
  override def run: IO[Unit] = for {
    dayOne <- loadFile("one")(DayOne.load)
    _      <- IO.println(s"DayOne Part1: ${DayOne.elfWithTheMostCalories(dayOne)}")
    _      <- IO.println(s"DayOne Part2: ${DayOne.elvesWithTheMostCalories(dayOne)}")

    dayTwoP1 <- loadFile("two")(DayTwo.V1.load)
    _        <- IO.println(s"DayTwo Part1: ${DayTwo.totalScore(dayTwoP1)}")
    dayTwoP2 <- loadFile("two")(DayTwo.V2.load)
    _        <- IO.println(s"DayTwo Part2: ${DayTwo.totalScore(dayTwoP2)}")

    dayThree <- loadFile("three")(DayThree.load)
    _        <- IO.println(
                  s"DayThree Part1: ${dayThree.map(DayThree.priorityOfCommon).sumAll}"
                )
    _        <- IO.println(s"DayThree Part2: ${DayThree.findBadges(dayThree).sumAll}")

  } yield ()

  private def loadFile[A](day: String)(loader: String => IO[A]): IO[A] =
    Resource
      .fromAutoCloseable(IO(Source.fromResource(s"day$day.txt")))
      .map(_.mkString)
      .use(loader)
}
