import java.nio.file.Files
import java.nio.file.StandardCopyOption.REPLACE_EXISTING

import scala.sys.process.Process

Global / stRemoteCache := RemoteCache.S3Aws(
  bucket = "scalablytyped-demos",
  region = "eu-central-1",
  prefix = Some("st-cache")
)

// Uncomment if you want to remove debug output
//Global / stQuiet := true

/** Custom task to start demo with webpack-dev-server, use as `<project>/start`.
  * Just `start` also works, and starts all frontend demos
  *
  * After that, the incantation is this to watch and compile on change:
  * `~<project>/fastOptJS::webpack`
  */
lazy val start = TaskKey[Unit]("start")

/** Say just `dist` or `<project>/dist` to make a production bundle in `docs`
  * for github publishing
  */
lazy val dist = TaskKey[File]("dist")

lazy val baseSettings: Project => Project =
  _.enablePlugins(ScalaJSPlugin)
    .settings(
      name := "opensea",
      version := "0.1",
      scalaVersion := "2.13.2",
      scalacOptions ++= ScalacOptions.flags,
      scalaJSUseMainModuleInitializer := true,
      /* disabled because it somehow triggers many warnings */
      scalaJSLinkerConfig := scalaJSLinkerConfig.value.withSourceMap(false),
      /* for slinky */
      libraryDependencies ++= Seq("me.shadaj" %%% "slinky-hot" % "0.6.5"),
      scalacOptions += "-Ymacro-annotations"
    )

lazy val hotReloadingSettings: Project => Project =
  _.enablePlugins(ScalaJSPlugin)
    .settings(
      fastOptJS / webpackDevServerExtraArgs := Seq("--inline", "--hot"),
      stIgnore += "react-proxy",
      Compile / npmDependencies ++= Seq(
        "react-proxy" -> "1.1.8"
      )
    )

addCommandAlias(
  "dev",
  ";opensea/fastOptJS::startWebpackDevServer;~opensea/fastOptJS"
)
lazy val `opensea` =
  project
    .enablePlugins(ScalablyTypedConverterPlugin)
    .configure(
      baseSettings,
      browserProject,
      reactNpmDeps,
      withCssLoading,
      bundlerSettings,
      hotReloadingSettings
    )
    .settings(
      useYarn := true,
      webpackDevServerPort := 8008,
      stFlavour := Flavour.Slinky,
      stReactEnableTreeShaking := Selection.All,
      Compile / npmDependencies ++= Seq(
        "@material-ui/core" -> "3.9.4", // note: version 4 is not supported yet
        "@material-ui/styles" -> "3.0.0-alpha.10", // note: version 4 is not supported yet
        "@material-ui/icons" -> "3.0.2",
        "recharts" -> "1.8.5",
        "@types/recharts" -> "1.8.10",
        "@types/classnames" -> "2.2.10",
        "react-router-dom" -> "5.1.2",
        "@types/react-router-dom" -> "5.1.2"
      )
    )

// specify versions for all of reacts dependencies to compile less since we have many demos here
lazy val reactNpmDeps: Project => Project =
  _.settings(
    stTypescriptVersion := "3.9.3",
    Compile / npmDependencies ++= Seq(
      "react" -> "16.13.1",
      "react-dom" -> "16.13.1",
      "@types/react" -> "16.9.42",
      "@types/react-dom" -> "16.9.8",
      "csstype" -> "2.6.11",
      "@types/prop-types" -> "15.7.3"
    )
  )

lazy val bundlerSettings: Project => Project =
  _.settings(
    Compile / fastOptJS / webpackDevServerExtraArgs += "--mode=development",
    Compile / fullOptJS / webpackDevServerExtraArgs += "--mode=production"
  )

lazy val withCssLoading: Project => Project =
  _.settings(
    /* custom webpack file to include css */
    webpackConfigFile := Some(
      (ThisBuild / baseDirectory).value / "custom.webpack.config.js"
    ),
    Compile / npmDevDependencies ++= Seq(
      "webpack-merge" -> "4.2.2",
      "css-loader" -> "3.4.2",
      "style-loader" -> "1.1.3",
      "file-loader" -> "5.1.0",
      "json-loader" -> "0.5.7",
      "url-loader" -> "3.0.0"
    )
  )

/** Implement the `start` and `dist` tasks defined above. Most of this is really
  * just to copy the index.html file around.
  */
lazy val browserProject: Project => Project =
  _.settings(
    start := {
      (Compile / fastOptJS / startWebpackDevServer).value
    },
    dist := {
      val artifacts = (Compile / fullOptJS / webpack).value
      val artifactFolder = (Compile / fullOptJS / crossTarget).value
      val distFolder =
        (ThisBuild / baseDirectory).value / "docs" / moduleName.value

      distFolder.mkdirs()
      artifacts.foreach { artifact =>
        val target = artifact.data.relativeTo(artifactFolder) match {
          case None          => distFolder / artifact.data.name
          case Some(relFile) => distFolder / relFile.toString
        }

        Files.copy(artifact.data.toPath, target.toPath, REPLACE_EXISTING)
      }

      val indexFrom = baseDirectory.value / "src/main/js/index.html"
      val indexTo = distFolder / "index.html"

      val indexPatchedContent = {
        import collection.JavaConverters._
        Files
          .readAllLines(indexFrom.toPath, IO.utf8)
          .asScala
          .map(_.replaceAllLiterally("-fastopt-", "-opt-"))
          .mkString("\n")
      }

      Files.write(indexTo.toPath, indexPatchedContent.getBytes(IO.utf8))
      distFolder
    }
  )
