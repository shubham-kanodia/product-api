package api.product

import scala.concurrent.{ExecutionContext, Future}

class ProductService(productDao: ProductDao)(implicit val ec: ExecutionContext) {
  def add(product: Product) = {
    productDao.create(product.pid, product.name, product.storeId)
  }

  def find(pid: Int): Future[Either[String, Product]] = {
    productDao.find(pid).map {
      case Some(product) => Right(product)
      case None => Left("No such product found")
    }
  }
}
