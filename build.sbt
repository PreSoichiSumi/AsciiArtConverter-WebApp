name := """asciiart-converter-server"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"
unmanagedBase := baseDirectory.value / "lib"
libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  evolutions,
  "commons-codec" % "commons-codec" % "1.10",
  "junit" % "junit" % "4.12",
  "org.webjars" % "ace" % "1.1.3",
  filters,
  "org.webjars" % "bootstrap" % "3.3.6",
  "org.webjars" % "jquery" % "2.1.4"
  //,
//  "com.github.oguna" % "aacj" % "1.0.0"
)

routesGenerator:=InjectedRoutesGenerator



//unmanagedSourceDirectories += project.in(file(".")).dependsOn(githubRepo)

//lazy val githubRepo = uri("git://github.com/oguna/asciiart-converter-java.git")
