package aoc

import cats.effect.Resource
import cats.effect.{IO, IOApp}

import scala.io.Source

object AdventOfCode2022 extends IOApp.Simple {
  override def run: IO[Unit] = for {
    dayOne <- Resource
      .fromAutoCloseable(IO(Source.fromResource("dayone.txt")))
      .map(_.mkString)
      .use(DayOne.load)

    _ <- IO.println(s"DayOne Part1: ${DayOne.elfWithTheMostCalories(dayOne)}")
    _ <- IO.println(s"DayOne Part2: ${DayOne.elvesWithTheMostCalories(dayOne)}")

  } yield ()
}
