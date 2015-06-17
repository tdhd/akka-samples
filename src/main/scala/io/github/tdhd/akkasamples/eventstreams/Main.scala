package io.github.tdhd.akkasamples.eventstreams

import language.postfixOps
import scala.concurrent.duration._
import scala.collection.mutable
import scala.concurrent.Future
import scala.util.{ Failure, Success, Try }
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.ActorLogging
import akka.actor.Cancellable
import akka.actor.Props

object DeathWatch {
  def main(args: Array[String]): Unit = {

    val system = ActorSystem("system")
    val mainActor = system.actorOf(Props[ParentActor], "parent")
    Thread.sleep(5000)
//    system.awaitTermination(5 seconds)
    system.shutdown()
  }
}
