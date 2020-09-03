package api.product

import spray.json.DefaultJsonProtocol

trait ProductFormats extends DefaultJsonProtocol {
  implicit val productProtocol = jsonFormat3(Product.apply)
}
