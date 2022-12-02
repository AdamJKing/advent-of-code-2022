package aoc

import cats.effect.Resource
import cats.effect.{IO, IOApp}

import scala.io.Source

object AdventOfCode2022 extends IOApp.Simple {
  override def run: IO[Unit] = for {
    dayOne <- loadFile("one")(DayOne.load)
    _      <- IO.println(s"DayOne Part1: ${DayOne.elfWithTheMostCalories(dayOne)}")
    _      <- IO.println(s"DayOne Part2: ${DayOne.elvesWithTheMostCalories(dayOne)}")

    dayTwo <- loadFile("two")(DayTwo.load)
    _      <- IO.println(s"DayTwo Part1: ${DayTwo.totalScore(dayTwo)}")

  } yield ()

  private def loadFile[A](day: String)(loader: String => IO[A]): IO[A] =
    Resource
      .fromAutoCloseable(IO(Source.fromResource(s"day$day.txt")))
      .map(_.mkString)
      .use(loader)
}
