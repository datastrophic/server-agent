import Dependencies._

lazy val root = Project(id = "agent", file("."))
  .settings(
    inThisBuild(List(
      organization := "io.datastrophic",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "agent",
    libraryDependencies += scalaTest % Test,
    mainClass in assembly := Some("io.datastrophic.isle.agent.Agent"),
    assemblyJarName in assembly := "agent.jar"
  )