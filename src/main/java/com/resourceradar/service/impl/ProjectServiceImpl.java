package com.resourceradar.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.ProjectDTO;
import com.resourceradar.entity.Manager;
import com.resourceradar.entity.Project;
import com.resourceradar.exception.ProjectNotFoundException;
import com.resourceradar.repository.ManagerRepository;
import com.resourceradar.repository.ProjectRepository;
import com.resourceradar.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ManagerRepository managerRepository;

	@Override
	public Project createProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public Project updateProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	@Override
	public Project getProjectById(String projectId) {
		return projectRepository.findById(projectId).orElse(null);
	}

	@Override
	public ProjectDTO assignManagerToProject(String projectId, Manager manager) {
		Manager mngr = managerRepository.save(manager);
		Project project=projectRepository.findById(projectId).orElse(null);
		project.setManager(mngr);

		String clientid = project.getClient().getId();
		String orgid = project.getOrgId();
		String employeeid = project.getManager().getEmployeeId();
		String type = project.getType();
		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime endDate =LocalDateTime.now();
		
		

		ProjectDTO prd = new ProjectDTO();
		prd.setId(projectId);
		prd.setType(mngr.getType());
		prd.setClientId(clientid);
		prd.setOrgId(orgid);
		prd.setProjectname(project.getName());		
		prd.setEmployeeId(employeeid);
		prd.setStartDate(startDate);
		prd.setEndDate(endDate);
		
		
		prd.setManager(mngr);
		projectRepository.save(project);

		return prd;
	}

	@Override
	public ProjectDTO getProjectManager(String projectId) {
		Project project = projectRepository.findById(projectId).orElse(null);

		String clientId= project.getClient().getId();
		String orgId = project.getOrgId();
		String employeeId = project.getManager().getEmployeeId();
		String type = project.getType();

		ProjectDTO projectDto = new ProjectDTO();
		projectDto.setClientId(clientId);
		projectDto.setEmployeeId(employeeId);
		projectDto.setOrgId(orgId);
		projectDto.setStartDate(project.getStartDate());
		projectDto.setEndDate(project.getEndDate());
		projectDto.setProjectname(project.getName());
		
		return projectDto;
	}

	@Override
	public Project updateProjectManager(String projectId, Manager manager) {
		Project project = projectRepository.findById(projectId).orElse(null);
		if (project != null) {
			project.setManager(manager);
			return projectRepository.save(project);
		}
		return null;
	}

	@Override
	public Page<Project> getAllProjects(Pageable pageable) throws ProjectNotFoundException {
		try {
			return projectRepository.findAll(pageable);
		} catch (Exception e) {
			throw new ProjectNotFoundException(" Project Details not found");
		}
	}
}
