package mappings
import slick.driver.JdbcProfile

trait DBProvider {

  val driver:JdbcProfile

  import driver.api._

  val db:Database

}
