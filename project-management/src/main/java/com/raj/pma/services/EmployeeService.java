package com.raj.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.pma.dao.EmployeeRepository;
import com.raj.pma.dto.EmployeeProject;
import com.raj.pma.entities.Employee;

@Service
public class EmployeeService {

	// This is example for field injection
	@Autowired
	EmployeeRepository empRepo;
	
	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}
	
	public Iterable<Employee> getAll(){
		return empRepo.findAll();
	}
	
	public List<EmployeeProject> employeeProjects(){
		return empRepo.employeeProjects();
	}
	
}
