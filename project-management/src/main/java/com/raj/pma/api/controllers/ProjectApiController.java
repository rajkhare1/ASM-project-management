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

import com.raj.pma.dao.ProjectRepository;
import com.raj.pma.entities.Project;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

	@Autowired
	ProjectRepository projectRepo;
	
	@GetMapping
	public Iterable<Project> getProjects(){
		return projectRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id) {
		return projectRepo.findById(id).get();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody Project project) {
		return projectRepo.save(project);
	}
	
	@PutMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody Project project) {
		return projectRepo.save(project);
	}
	
	@PatchMapping(value="/{id}",consumes="application/json")
	public Project patchUpdate(@PathVariable("id") Long id, @RequestBody Project patchProj) {
		Project proj = projectRepo.findById(id).get();
		
		if(proj.getName() !=null) {
			proj.setName(patchProj.getName());
		}
		
		if(proj.getStage() !=null) {
			proj.setStage(patchProj.getStage());
		}
		
		if(proj.getDescription() !=null) {
			proj.setDescription(patchProj.getDescription());
		}
		return projectRepo.save(proj);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		try {
			projectRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			
		}
	}
}
