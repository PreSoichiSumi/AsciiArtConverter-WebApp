name := """asciiart-converter-server"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"
unmanagedBase := baseDirectory.value / "lib"

//キャッシュが残るのでバージョン変更時は
// activator clean-> activator run
libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  evolutions,
  "commons-codec" % "commons-codec" % "1.10",
  "junit" % "junit" % "4.12",
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" % "ace" % "1.2.0",
  filters,
  "org.webjars" % "bootstrap" % "3.3.6",
  "org.webjars" % "jquery" % "2.2.2",
  "org.webjars.bower" % "bootstrap-fileinput" % "4.3.1",
  "org.webjars" % "bootstrap-slider" % "5.3.1",
  "com.mohiva" %% "play-html-compressor" % "0.6.1"
  //,
//  "com.github.oguna" % "aacj" % "1.0.0"
)
herokuAppName in Compile := "asciiart-converter"

routesGenerator:=InjectedRoutesGenerator
resolvers+="webjars" at "http://webjars.github.com/m2"

pipelineStages := Seq(rjs, uglify, digest, gzip)

//unmanagedSourceDirectories += project.in(file(".")).dependsOn(githubRepo)

//lazy val githubRepo = uri("git://github.com/oguna/asciiart-converter-java.git")
