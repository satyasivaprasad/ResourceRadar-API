package com.resourceradar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.ProjectDTO;
import com.resourceradar.entity.Manager;
import com.resourceradar.entity.Project;
import com.resourceradar.exception.ProjectNotFoundException;

@Service
public interface ProjectService {

	 Project createProject(Project project);
	    Project updateProject(Project project);
	    List<Project> getAllProjects();
	    Project getProjectById(String projectId);
	    ProjectDTO assignManagerToProject(String projectId, Manager manager);
	    ProjectDTO getProjectManager(String projectId);
	    Project updateProjectManager(String projectId, Manager manager);
	    
	Page<Project> getAllProjects(Pageable pageable) throws ProjectNotFoundException;

}
