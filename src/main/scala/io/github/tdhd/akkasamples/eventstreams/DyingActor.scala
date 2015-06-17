package io.github.tdhd.akkasamples.eventstreams

import language.postfixOps
import scala.concurrent.duration._
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorLogging
import akka.actor.Props
import akka.actor.Terminated
import akka.actor.Cancellable

case class DyingCry(message: String)

object DyingActor {
  def props(ttl: FiniteDuration): Props = Props(new DyingActor(ttl))
}

class DyingActor(timeToLive: FiniteDuration) extends Actor with ActorLogging {
  import context.dispatcher

  // schedule to die!
  val scheduler: Cancellable = context.system.scheduler.scheduleOnce(timeToLive)(die)

  def die() = {
    context.system.eventStream.publish(DyingCry(s"${context.parent} let me die!"))
    scheduler.cancel
    context.stop(self)
  }

  def receive = {
    case _ =>
  }
}
