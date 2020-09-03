package api.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import api.config.Config.ProductsConfig
import api.data.flyway.FlywayIntegration
import api.product.{ProductDao, ProductService}
import api.routes.ProductsRoute

import scala.concurrent.{ExecutionContext, Future}

class Server(implicit val system: ActorSystem,
             implicit val materializer: ActorMaterializer)
  extends ProductsRoute with ProductsConfig {

  implicit val ec = ExecutionContext.global

  val productDao = new ProductDao()

  val productService = new ProductService(productDao)

  def startServer(): Future[Http.ServerBinding] = {
    Http().bindAndHandle(route, httpHost, httpPort)
  }
}

object Server extends App with FlywayIntegration {

  implicit val system = ActorSystem("RestServer")
  implicit val materializer = ActorMaterializer()

  val server = new Server()

  server.startServer()
}
