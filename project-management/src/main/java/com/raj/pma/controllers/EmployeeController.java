
package com.raj.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raj.pma.entities.Employee;
import com.raj.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		Employee anEmployee = new Employee();
		model.addAttribute("employee", anEmployee);
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		empService.save(employee);
		
		// use the redirect to prevent duplicate submission 
		return "redirect:/employees/new";
	}
	
	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees = empService.getAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}
}
