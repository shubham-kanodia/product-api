package api.data.flyway

import api.config.Config.ProductsConfig
import org.flywaydb.core.Flyway

import scala.util.Try

class FlywayService(jdbcUrl: String, dbUser: String, dbPassword: String) {
  private val flyway = new Flyway()
  flyway.setDataSource(jdbcUrl, dbUser, dbPassword)

  def migrateDatabaseSchema(): Int = Try(flyway.migrate()).getOrElse {
    flyway.baseline()
    flyway.repair()
    flyway.migrate()
  }
}

trait FlywayIntegration extends ProductsConfig {
  val flyWayService = new FlywayService(jdbcUrl, dbUser, dbPassword)

  flyWayService.migrateDatabaseSchema()
}