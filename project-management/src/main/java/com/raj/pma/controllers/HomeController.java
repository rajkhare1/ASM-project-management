package com.raj.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raj.pma.dao.EmployeeRepository;
import com.raj.pma.dao.ProjectRepository;
import com.raj.pma.dto.ChartData;
import com.raj.pma.dto.EmployeeProject;
import com.raj.pma.entities.Project;

@Controller
public class HomeController {
	
	
@Value("${version}")
private String ver;
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	

	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("versionNumber", ver);
		
		Map<String, Object> map = new HashMap<>();
		
		// we are querying database for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects",projects);
		
		List<ChartData> projectChartData = proRepo.getProjectStatus();
		
		// Lets convert projectChartData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectChartData);
		//[["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 3]]
		model.addAttribute("projectStatusCount", jsonString);
		
		
		
		// we are querying database for employees
		List<EmployeeProject> employeesProjectCount = empRepo.employeeProjects();
		model.addAttribute("employeesProjectCount", employeesProjectCount);
		return "main/home";
	}
}
