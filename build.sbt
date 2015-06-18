val project = Project(
  id = "akka-samples-id",
  base = file("."),
  settings = Project.defaultSettings ++
             Seq(
               name := """akka-samples""",
               scalaVersion := "2.11.6", // "2.10.5", //"2.11.6",
               scalacOptions in Compile ++= Seq("-encoding", "UTF-8", "-target:jvm-1.7", "-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint"),
               javaOptions in run ++= Seq("-Xms128m", "-Xmx1024m"),
               libraryDependencies ++= Seq(
                 "com.typesafe.akka" %% "akka-actor" % "2.3.11",
                 "com.typesafe.akka" %% "akka-persistence-experimental" % "2.3.11",
                 // for usage in the persistence example
                 "org.iq80.leveldb"            % "leveldb"          % "0.7",
                 "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8",
                 "com.typesafe.akka" % "akka-http-core-experimental_2.11" % "1.0-RC3"
                 )
             )
)

