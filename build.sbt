import Dependencies._

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "aoc"

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code-2022",
    libraryDependencies ++= Seq(catsCore, catsEffect, atto, scalaTest % Test)
  )
