name := """form-test"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies += "commons-io" % "commons-io" % "2.3"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  jdbc,
  javaWs,
  "commons-io" % "commons-io" % "2.3",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
  "mysql" % "mysql-connector-java" % "5.1.18"


)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
