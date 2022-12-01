import sbt._

object Dependencies {
  lazy val catsCore = "org.typelevel" %% "cats-core" % "2.9.0"
  lazy val catsEffect = "org.typelevel" %% "cats-effect" % "3.4.1"
  lazy val atto = "org.tpolecat" %% "atto-core" % "0.9.5"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.14"
}
