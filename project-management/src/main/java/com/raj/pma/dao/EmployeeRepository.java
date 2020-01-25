package com.raj.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.raj.pma.dto.EmployeeProject;
import com.raj.pma.entities.Employee;



public interface EmployeeRepository extends CrudRepository<Employee,Long>{
	
	@Override
	List<Employee> findAll();
	
	@Query(nativeQuery = true,value = "SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount " + 
			"FROM employee e " + 
			"LEFT JOIN project_employee pe " + 
			"ON pe.employee_id = e.employee_id " + 
			"GROUP BY e.first_name, e.last_name " + 
			"ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();

}
