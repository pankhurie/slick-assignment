package components

import mappings.{DBProvider, Project, ProjectTable}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ProjectComponent extends ProjectTable{
  this:DBProvider =>
  import driver.api._

  def create = db.run(projectTableQuery.schema.create)

  def insert(project: Project) = db.run(projectTableQuery += project)


  def updateName(id:Int, name:String) : Future[Int] = {
    val query = employeeTableQuery.filter(x => x.id === id)
      .map(_.name).update(name)
    db.run(query)
  }

  def insertOrUpdate(project: Project) ={
    val query = projectTableQuery.insertOrUpdate(project)
    db.run(query)
  }

}
