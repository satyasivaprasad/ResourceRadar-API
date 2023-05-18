package com.resourceradar.service;

import java.util.List;

import com.resourceradar.entity.Project;
import com.resourceradar.exception.ProjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

//	public List<Project> getAllProjects() throws ProjectNotFoundException;

	List<Project> findAllProjectsBYClientId(String clientId);

	public List<Project> getAllProjects() throws ProjectNotFoundException;

	public List<Project> getProjectBasedOnClineId(String clientId);

	List<Project> getProjectBasedOnClientId(String clientId);
	Page<Project> getAllProjects(Pageable pageable) throws ProjectNotFoundException;

}
