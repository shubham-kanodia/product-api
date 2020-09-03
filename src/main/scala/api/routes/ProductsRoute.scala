package api.routes

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import api.product.{Product, ProductFormats, ProductService}

trait ProductsRoute extends ProductFormats with SprayJsonSupport {
  def productService: ProductService

  implicit val system: ActorSystem
  implicit val materializer: ActorMaterializer

  val route: Route = {
    path("create") {
      post {
        entity(as[Product]) { inp =>
          productService.add(inp)
          complete(StatusCodes.OK)
        }
      }
    } ~
      path("product" / IntNumber) { pid =>
        get {
          onSuccess(productService.find(pid)) {
            case Right(product) => complete((StatusCodes.OK, product))
            case Left(errorMsg) => complete((StatusCodes.NotFound, errorMsg))
          }
        }
      }
  }
}
