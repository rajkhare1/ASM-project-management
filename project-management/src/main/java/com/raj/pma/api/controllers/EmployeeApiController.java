package com.raj.pma.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.raj.pma.dao.EmployeeRepository;
import com.raj.pma.entities.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public Iterable<Employee> getEmployees(){
		return empRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id)  {
		return empRepo.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody Employee employee) {
		return empRepo.save(employee);
	}
	
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee update(@RequestBody Employee employee){
		return empRepo.save(employee);
	}
	
	@PatchMapping(value="/{id}", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee partialUpdate(@RequestBody Employee patchEmp, @PathVariable("id") Long id) {
		Employee emp = empRepo.findById(id).get();
		
		if(emp.getEmail() != null) {
			emp.setEmail(patchEmp.getEmail());
		}
		
		if(emp.getFirstName() !=null) {
			emp.setFirstName(patchEmp.getFirstName());
		}
		
		if(emp.getLastName() !=null) {
			emp.setLastName(patchEmp.getLastName());
		}
		return empRepo.save(emp);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		
		try {
			empRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			
		}
	}

}
