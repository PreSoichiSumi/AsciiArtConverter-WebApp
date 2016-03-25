name := """asciiart-converter-server"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"
unmanagedBase := baseDirectory.value / "lib"
libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs//,
//  "com.github.oguna" % "aacj" % "1.0.0"
)





//unmanagedSourceDirectories += project.in(file(".")).dependsOn(githubRepo)

//lazy val githubRepo = uri("git://github.com/oguna/asciiart-converter-java.git")
