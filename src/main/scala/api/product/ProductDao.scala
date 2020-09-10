package api.product

import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class ProductDao(implicit val ex: ExecutionContext) {

  val driver: JdbcProfile = slick.jdbc.PostgresProfile

  import driver.api._

  lazy val db: Database = Database.forConfig("database")

  private class ProductTable(tag: Tag) extends Table[Product](tag, "products") {
    def pid = column[Int]("pid", O.PrimaryKey)

    def name = column[String]("name")

    def storeId = column[String]("storeid")

    def * = (pid, name, storeId) <> (Product.tupled, Product.unapply)

  }

  private val products = TableQuery[ProductTable]

  def create(product: Product) = db.run {
    products returning products += product
  }

  def find(productId: Int): Future[Option[Product]] = {
    val findAction = products.filter { p =>
      p.pid === productId
    }.result.map(_.headOption)

    db.run(findAction)
  }

}
