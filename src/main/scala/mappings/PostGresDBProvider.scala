package mappings
import slick.driver.PostgresDriver
/**
  * Created by knoldus on 10/3/17.
  */
trait PostGresDBProvider extends DBProvider{
  val driver = PostgresDriver

  import driver.api._

  val db = Database.forConfig("myPostgresDB")

}
