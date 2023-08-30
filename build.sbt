name := "MySparkProject"
version := "1.0"
scalaVersion := "2.12.17"

val tokenSource = TokenSource.GitConfig("github.token") || TokenSource
  .Environment("GITHUB_TOKEN")

resolvers += Resolver.githubPackages("SavvyTools")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.3.0",
  "org.apache.spark" %% "spark-sql" % "3.3.0",
  "software.amazon.awssdk" % "sfn" % "2.20.118",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  "org.savvytools" %% "ezmacros" % "0.1.0-SNAPSHOT",
  "org.savvytools" % "ezaspects" % "1.0"


)
assembly / assemblyJarName := s"myspark-assembly.jar"
assembly / assemblyMergeStrategy := {
  case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
  case m if m.endsWith("README.md")               => MergeStrategy.discard
  case _                                          => MergeStrategy.first
}

