package com.raj.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.pma.dao.ProjectRepository;
import com.raj.pma.dto.ChartData;
import com.raj.pma.entities.Project;

@Service
public class ProjectService {
  
	@Autowired
	ProjectRepository proRepo;
	
	public Project save(Project project){
		return proRepo.save(project);
	}
	
	public Iterable<Project> getAll(){
		return proRepo.findAll();
	}
	
	public List<ChartData> getProjectStatus(){
		return proRepo.getProjectStatus();
	}
	
	
}
