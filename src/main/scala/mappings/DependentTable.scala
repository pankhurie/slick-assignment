package mappings

import slick.ast.Subquery.Default

/**
  * Created by knoldus on 13/3/17.
  */
trait DependentTable extends EmployeeTable{
  this:DBProvider =>
  import driver.api._
  class DependentTable(tag: Tag) extends Table[Dependent](tag, "dependent") {
    val name = column[String]("name")
    val emp_id = column[Int]("emp_id")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age")
    def empForeignKey = foreignKey("emp_dependent_fk", emp_id, employeeTableQuery)(_.id)
    def * = (emp_id, name, relation, age) <>(Dependent.tupled, Dependent.unapply)

  }

  val dependentTableQuery = TableQuery[DependentTable]

}

case class Dependent(emp_id:Int, name: String, relation:String, age: Option[Int] = None)
