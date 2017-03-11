package com.example
import components.{ProjectComponent, EmployeeComponent}
import mappings.Employee
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.PostgresProfile.api._
import mappings.Project

object Hello extends App{

  EmployeeComponent.create
  ProjectComponent.create
  ProjectComponent.insert(Project(2,"p1"))
  Thread.sleep(1000)

}
