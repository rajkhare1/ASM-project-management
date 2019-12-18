package com.raj.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.raj.pma.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	@Override
	List<Project> findAll();

}
