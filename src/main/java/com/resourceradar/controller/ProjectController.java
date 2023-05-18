package com.resourceradar.controller;

import java.util.List;

import com.resourceradar.entity.Designation;
import com.resourceradar.exception.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.resourceradar.config.EndPointConfig;
import com.resourceradar.entity.Project;
import com.resourceradar.service.ProjectService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(EndPointConfig.API_V1)
@Tag(name = "project_tbl")
@Slf4j
public class ProjectController {
	
	@Autowired
	public ProjectService projectService;

	@GetMapping(EndPointConfig.PROJECT_DETAILS)
	public ResponseEntity<List<Project>> getAllProjects() throws ProjectNotFoundException {
        List<Project> projects = projectService.getAllProjects();
		if (projects.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(projects, HttpStatus.OK);
		}
	}
	@GetMapping(EndPointConfig.PROJECT_DETAILS_SEARCH)
	public ResponseEntity<List<Project>> searchProjectsByClient(@RequestParam("clientId") String clientId)
			throws ProjectNotFoundException {

		List<Project> projects = projectService.getProjectBasedOnClientId(clientId);
		if (projects.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(projects, HttpStatus.OK);
		}
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
	@GetMapping("/projectc/client/{clientId}")
	public List<Project> getAllProjects(@PathVariable String clientId){
		List<Project> findAllProjectsBYClientId = projectService.findAllProjectsBYClientId(clientId);
		return findAllProjectsBYClientId;
	}

}
