package org.http4s.client

import cats.effect._
import org.http4s.client.finagle._
import com.twitter.finagle.Http

class FinagleClientSpec extends ClientRouteTestBattery("FinagleClient") {
  def clientResource: Resource[IO, Client[IO]] = {
    ClientFactory.cached(ClientFactory(Http.client.withStreaming(true)))
      .map(FinagleClient.fromServiceFactory(_))
      .unsafeRunSync()
  }
}

