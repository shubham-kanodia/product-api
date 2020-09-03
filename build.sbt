lazy val products = (project in file("."))
  .settings(
    name := "products",
    version := "1.0",
    scalaVersion := "2.11.11",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %%"akka-http-testkit-experimental" % "1.0",
      "org.scalatest" %% "scalatest" % "3.0.8" % Test,
      "junit" % "junit" % "4.12" % Test,
      "com.typesafe.akka" %% "akka-http-experimental" % "1.0",
      "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "1.0",
      "org.postgresql" % "postgresql" % "42.2.2",
      "com.typesafe.slick" %% "slick" % "3.2.3",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",
      "org.postgresql" % "postgresql" % "9.4.1211",
      "com.h2database" % "h2" % "1.4.192" % "test",
      "org.flywaydb" % "flyway-core" % "3.2.1"
    )
  )
