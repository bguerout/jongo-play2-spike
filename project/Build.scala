import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "play-jongo"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
       "org.mongodb" % "mongo-java-driver" % "2.8.0",
       "org.jongo" % "jongo" % "0.3"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here      
      publishArtifact in(Compile, packageDoc) := false
    )

}
