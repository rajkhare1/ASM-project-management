package com.raj.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.raj.pma.dao.EmployeeRepository;
import com.raj.pma.dao.ProjectRepository;
import com.raj.pma.entities.Employee;
import com.raj.pma.entities.Project;

@Controller
public class HomeController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	

	@GetMapping("/")
	public String displayHome(Model model) {
		// we are querying database for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects",projects);
		
		// we are querying database for employees
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employees", employees);
		return "main/home";
	}
}
