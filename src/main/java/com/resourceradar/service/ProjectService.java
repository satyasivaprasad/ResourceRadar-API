package com.resourceradar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.resourceradar.dto.ProjectDTO;
import com.resourceradar.dto.projectGetDTOResponse;

@Service
public interface ProjectService {

	ProjectDTO createProject(ProjectDTO projectDTO);

	ProjectDTO updateProject(String projectId, ProjectDTO project);

	List<projectGetDTOResponse> getAllProjects();

	projectGetDTOResponse getProjectById(String projectId);

//	ProjectPostDTO assignManagerToProject(String projectId, ManagerPostDTO managerDTO);
//
//	projectGetDTOResponse getProjectManager(String projectId);
//
//	ManagerDto updateProjectManager(String projectId, ManagerPostDTO manager);
//
//	Page<Project> getAllProjects(Pageable pageable) throws ProjectNotFoundException;

}
