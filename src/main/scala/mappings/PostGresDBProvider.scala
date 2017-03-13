package mappings
import slick.driver.PostgresDriver

trait PostGresDBProvider extends DBProvider{

  val driver = PostgresDriver

  import driver.api._

  val db = Database.forConfig("myPostgresDB")

}
