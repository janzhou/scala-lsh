name := "scala-lsh"
version := "0.1.x"
organization := "org.janzhou"

scalaVersion := "2.12.1"
crossScalaVersions := Seq("2.11.8", "2.12.1")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % Test
)

scalacOptions in (Compile, doc) := Seq(
  "-doc-title", "Locality Sensitive Hash",
  "-sourcepath", (baseDirectory in ThisBuild).value.toString,
  "-doc-source-url", s"https://github.com/janzhou/${name.value}/tree/${version.value}€{FILE_PATH}.scala"
)
