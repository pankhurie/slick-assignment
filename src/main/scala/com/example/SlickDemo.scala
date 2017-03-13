package com.example
import components.{DependentComponent, ProjectComponent, EmployeeComponent}
import mappings.{Employee, Project, Dependent}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


object SlickDemo extends App{

  EmployeeComponent.create
  ProjectComponent.create
  DependentComponent.create

  EmployeeComponent.insert(Employee(1, "Pankhurie", 2.3D))
  EmployeeComponent.insert(Employee(2, "Anmol", 1.3D))
  EmployeeComponent.insert(Employee(3, "Shubhra", 4.3D))
  EmployeeComponent.insert(Employee(4, "Shivangi", 0.3D))
  EmployeeComponent.insert(Employee(5, "Mahesh", 6.3D))
  EmployeeComponent.insert(Employee(6, "Neha", 4.3D))
  EmployeeComponent.insert(Employee(7, "Prashant", 0.3D))
  EmployeeComponent.insert(Employee(8, "Akhil", 1.3D))
  EmployeeComponent.insert(Employee(9, "Ashish", 5.3D))

  ProjectComponent.insert(Project(1, "Scala"))
  ProjectComponent.insert(Project(1, "JQuery-Ajax"))
  ProjectComponent.insert(Project(2, "Testing"))
  ProjectComponent.insert(Project(2, "Bootstrap"))
  ProjectComponent.insert(Project(3, "Scala"))
  ProjectComponent.insert(Project(4, "Bootstrap"))

  DependentComponent.insert(Dependent(1, "Navya", "Sister"))
  DependentComponent.insert(Dependent(1, "Saksham", "Brother", Some(17)))
  DependentComponent.insert(Dependent(2, "Ashia", "Sister", Some(19)))
  DependentComponent.insert(Dependent(2, "Anil", "Brother", Some(5)))
  DependentComponent.insert(Dependent(3, "Shubhangi", "Sister", Some(9)))
  DependentComponent.insert(Dependent(4, "Shubham", "Brother", Some(15)))

  val allEmployees = EmployeeComponent.getAll()
  val allProjects = ProjectComponent.getAll()
  val allDependents = DependentComponent.getAll()
  println("All Employees:")
  allEmployees map println
  Thread.sleep(1000)

  println("All Projects:")
  allProjects map println
  Thread.sleep(1000)

  println("All Dependents:")
  allDependents map println

  Thread.sleep(1000)

}
