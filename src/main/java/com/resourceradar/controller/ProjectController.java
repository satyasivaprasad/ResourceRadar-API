package com.resourceradar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resourceradar.config.EndPointConfig;
import com.resourceradar.dto.ManagerDto;
import com.resourceradar.dto.ProjectDTO;
import com.resourceradar.dto.ProjectPostDTO;
import com.resourceradar.dto.ProjectsDTO;
import com.resourceradar.entity.Manager;
import com.resourceradar.entity.Project;
import com.resourceradar.exception.ProjectNotFoundException;
import com.resourceradar.service.ProjectService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(EndPointConfig.API_V1 + EndPointConfig.PROJECT_DETAILS)
@Tag(name = "project")
@Slf4j
public class ProjectController {
	
	@Autowired
	public ProjectService projectService;

	@PostMapping()
    public String createProject(@RequestBody ProjectDTO projectDTO) {
		Project createdProject = projectService.createProject(projectDTO);
	    return "Project details saved successfully. Project ID: " + createdProject.getId();
	}
	
	@PutMapping("/{projectId}/project")
	public Project updateProject(@PathVariable String projectId, @RequestBody Project project) {
	    Project existingProject = projectService.updateProject(projectId,project);
	    return existingProject;
	}

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable String projectId) {
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

	@GetMapping()
	public ResponseEntity<List<Project>> getAllProjects() throws ProjectNotFoundException {
        List<Project> projects = projectService.getAllProjects();
		if (projects.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(projects, HttpStatus.OK);
		}
	}
	@PostMapping("/{projectId}/manager")
	public ProjectPostDTO assignManagerToProject(@PathVariable String projectId, @RequestBody Manager manager) {
		ProjectPostDTO project = projectService.assignManagerToProject(projectId,manager);
	    return project;
	}

	
	
	@GetMapping("{projectId}/manager")
	public ProjectsDTO getProjectManager(@PathVariable String projectId) {
	    ProjectsDTO project = projectService.getProjectManager(projectId);
	    
	    return project;
	}
	
	
	@PutMapping("/{projectId}/manager")
	public ManagerDto updateProjectManager(@PathVariable String projectId, @RequestBody Manager manager) {
		ManagerDto updateProjectManager = projectService.updateProjectManager(projectId, manager);
		return updateProjectManager;
	}
	
	
	public ResponseEntity<Page<Project>> getAllProjects(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
	) throws ProjectNotFoundException {
		Pageable pageable = PageRequest.of(page, size);
		Page<Project> projects = projectService.getAllProjects(pageable);

		if (projects.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(projects, HttpStatus.OK);
		}
	}
}
