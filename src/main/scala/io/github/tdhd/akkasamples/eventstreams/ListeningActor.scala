package io.github.tdhd.akkasamples.eventstreams

import language.postfixOps
import scala.concurrent.duration._
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorLogging
import akka.actor.Props
import akka.actor.Terminated
import akka.actor.Cancellable

object ListeningActor {
  def props(): Props = Props(new ListeningActor())
}

class ListeningActor extends Actor with ActorLogging {
  context.system.eventStream.subscribe(self, classOf[DyingCry])

  def receive = {
    case DyingCry(msg) => log.info("Received DyingCry({})", msg)
  }

  override def postStop(): Unit = {
    log.info("Dying!")
    context.system.eventStream.unsubscribe(self)
  }
}
