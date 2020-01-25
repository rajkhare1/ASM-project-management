package com.raj.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raj.pma.entities.Employee;
import com.raj.pma.entities.Project;
import com.raj.pma.services.EmployeeService;
import com.raj.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;

	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project();
		model.addAttribute("project", aProject);
		
		List<Employee> employees = empService.getAll();
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		
		proService.save(project);
		
		// use the redirect to prevent duplicate submission 
		return "redirect:/projects";
	}
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proService.getAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	
}
