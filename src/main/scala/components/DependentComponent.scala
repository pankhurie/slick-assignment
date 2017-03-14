package components

import mappings.{MySqlDBProvider, DBProvider, Dependent, DependentTable}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait DependentComponent extends DependentTable{
  this:DBProvider =>
  import driver.api._

  def create = db.run(dependentTableQuery.schema.create)

  def insert(dependent: Dependent) = db.run(dependentTableQuery += dependent)

  def deleteByEmployee(empId: Int) = {
    val query = dependentTableQuery.filter(x => x.emp_id === empId)
    val action = query.delete //delete records which are les than 4
    db.run(action)
  }

  def delete(dependent: Dependent) = {
    val query = dependentTableQuery.filter(x => x.emp_id === dependent.emp_id && x.name ===dependent.name && x.relation === dependent.relation)
    val action = query.delete //delete records which are les than 4
    db.run(action)
  }

  def updateAge(dependent: Dependent, age:Int) = {
    val query = dependentTableQuery.filter(x =>  x.emp_id === dependent.emp_id && x.name ===dependent.name && x.relation === dependent.relation)
      .map(_.age).update(Some(age))
    db.run(query)
  }

  def updateName(dependent: Dependent, name: String) = {
    val query = dependentTableQuery.filter(x =>  x.emp_id === dependent.emp_id && x.name ===dependent.name && x.relation === dependent.relation)
      .map(_.name).update(name)
    db.run(query)
  }

  def updateRelation(dependent: Dependent, relation: String) = {
    val query = dependentTableQuery.filter(x =>  x.emp_id === dependent.emp_id && x.name ===dependent.name && x.relation === dependent.relation)
      .map(_.relation).update(relation)
    db.run(query)
  }

  def insertOrUpdate(dependent:Dependent) ={
    val query = dependentTableQuery.insertOrUpdate(dependent)
    db.run(query)
  }

  def getAllByEmployeeId(empId:Int)={
    val query = dependentTableQuery.filter(x => x.emp_id === empId).to[List].result
    db.run(query)
  }

  def getAll()= {
    val query = dependentTableQuery.to[List].result
    db.run(query)
  }

}

object DependentComponent extends DependentComponent with MySqlDBProvider