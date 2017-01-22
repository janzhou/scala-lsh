import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "org.janzhou",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "LSH",
    scalacOptions in Compile in doc ++= Seq(
      "-doc-title", "Locality Sensitive Hash"
    ),
    libraryDependencies += scalaTest % Test
  )
