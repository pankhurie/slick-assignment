package components

import mappings.{DBProvider, Employee, EmployeeTable}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


object EmployeeComponent extends EmployeeTable{

  this:DBProvider =>
  import driver.api._

  def create = db.run(employeeTableQuery.schema.create)

  def insert(employee: Employee) = db.run(employeeTableQuery += employee)

  def delete(id: Int) = {
    val query = employeeTableQuery.filter(x => x.id === id)
    val action = query.delete //delete records which are les than 4
    db.run(action)
  }

  def updateName(id:Int, name:String) = {
    val query = employeeTableQuery.filter(x => x.id === id)
      .map(_.name).update(name)
    db.run(query)
  }

  def updateExperience(id:Int, experience:Double) = {
    val query = employeeTableQuery.filter(x => x.id === id)
      .map(_.experience).update(experience)
    db.run(query)
  }

  def insertOrUpdate(employee:Employee) ={
    val query = employeeTableQuery.insertOrUpdate(employee)
    db.run(query)
  }

  def getFreshers() = {
    val query = employeeTableQuery.filter(x => x.experience < 1D).to[List].result
    db.run(query)
  }

  def getAll()= {
    val query = employeeTableQuery.to[List].result
    db.run(query)
  }

}
