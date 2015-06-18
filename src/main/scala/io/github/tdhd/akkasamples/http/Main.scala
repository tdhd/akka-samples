package io.github.tdhd.akkasamples.http

// lifted straight out of
// http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0-RC3/scala/http/routing-dsl/index.html

import akka.stream.ActorFlowMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
//import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport._
import akka.actor.ActorSystem

object Main extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorFlowMaterializer()

  val route =
    path("hello") {
      get { rc =>
        println(s"Received HTTP request: ${rc.request}")
        rc.complete("Hello from akka-http")

//        complete {
//          <h1>Say hello to akka-http</h1>
//        }
      }
    } ~
    path("new") {
      get {
        complete("Hello from new path")
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 9090)

  println(s"Server online at http://localhost:9090/\nPress RETURN to stop...")
  scala.io.StdIn.readLine()

  import system.dispatcher // for the future transformations
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ ⇒ system.shutdown()) // and shutdown when done
}

