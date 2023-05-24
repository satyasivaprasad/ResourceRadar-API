package com.resourceradar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.ManagerDto;
import com.resourceradar.dto.ProjectDTO;
import com.resourceradar.dto.ProjectPostDTO;
import com.resourceradar.dto.ProjectsDTO;
import com.resourceradar.entity.Manager;
import com.resourceradar.entity.Project;
import com.resourceradar.exception.ProjectNotFoundException;

@Service
public interface ProjectService {

	 Project createProject(ProjectDTO projectDTO);
	    Project updateProject(String projectId,Project project);
	    List<Project> getAllProjects();
	    Project getProjectById(String projectId);
	    ProjectPostDTO assignManagerToProject(String projectId, Manager manager);
	    ProjectsDTO getProjectManager(String projectId);
	    ManagerDto updateProjectManager(String projectId, Manager manager);
	    
	Page<Project> getAllProjects(Pageable pageable) throws ProjectNotFoundException;

}
