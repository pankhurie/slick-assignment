package components

import mappings.{DBProvider, Employee, EmployeeTable}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


object EmployeeComponent extends EmployeeTable{

  this:DBProvider =>
  import driver.api._
  def create = db.run(employeeTableQuery.schema.create)

  def insert(employee: Employee) = db.run(employeeTableQuery += employee)

  def delete(exp: Double) = {
    val query = employeeTableQuery.filter(x => x.experience < exp)
    val action = query.delete //delete records which are les than 4
    db.run(action)
  }

  def updateName(id:Int, name:String) : Future[Int] = {
    val query = employeeTableQuery.filter(x => x.id === id)
      .map(_.name).update(name)
    db.run(query)
  }

  def insertOrUpdate(employee:Employee) ={
    val query = employeeTableQuery.insertOrUpdate(employee)
    db.run(query)
  }
    //insert with tuple
  //  def insertMultiple(list: List[Employee]) = db.run(employeeTableQuery.)
}