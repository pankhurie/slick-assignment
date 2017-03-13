package mappings

import slick.driver.MySQLDriver

/**
  * Created by knoldus on 13/3/17.
  */
trait MySqlDBProvider extends DBProvider{
  val driver = MySQLDriver

  import driver.api._

  val db = Database.forConfig("myMySqlDB")

}
