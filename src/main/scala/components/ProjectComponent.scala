package components

import mappings.{MySqlDBProvider, DBProvider, Project, ProjectTable}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ProjectComponent extends ProjectTable{
  this:DBProvider =>
  import driver.api._

  def create = db.run(projectTableQuery.schema.create)

  def insert(project: Project) = db.run(projectTableQuery += project)

  def delete(project: Project) = {
    val query = projectTableQuery.filter(x => x.emp_id === project.emp_id && x.name === project.name)
    val action = query.delete //delete records which are les than 4
    db.run(action)
  }

  def updateName(empId: Int, oldName:String, newName:String) : Future[Int] = {
    val query = projectTableQuery.filter(x => x.emp_id === empId && x.name === oldName)
      .map(_.name).update(newName)
    db.run(query)
  }

  def insertOrUpdate(project: Project) ={
    val query = projectTableQuery.insertOrUpdate(project)
    db.run(query)
  }

  def getEmployeeProjects(id: Int) = {
    val query = projectTableQuery.filter(x => x.emp_id === id).to[List].result
    db.run(query)
  }

  def getAll()= {
    val query = projectTableQuery.to[List].result
    db.run(query)
  }

}

object ProjectComponent extends ProjectComponent with MySqlDBProvider