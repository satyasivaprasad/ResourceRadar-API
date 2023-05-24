package com.resourceradar.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.ManagerDto;
import com.resourceradar.dto.ProjectDTO;
import com.resourceradar.dto.ProjectPostDTO;
import com.resourceradar.dto.ProjectsDTO;
import com.resourceradar.entity.Client;
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
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Project createProject(ProjectDTO projectDTO) {
		Project map = mapper.map(projectDTO, Project.class);
		return projectRepository.save(map);
	}
	@Override
	public Project updateProject(String projectId,Project project) {
		Project pro = projectRepository.findById(projectId).orElse(null);
		
		pro.setName(project.getName());
		pro.setType(project.getType());
		
		Project save = projectRepository.save(pro);
		return save;
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
	public ProjectPostDTO assignManagerToProject(String projectId, Manager manager) {
		
		Manager mngr = managerRepository.save(manager);
		Project project = projectRepository.findById(projectId).orElse(null);
		
		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime endDate =LocalDateTime.now();
		
		project.setManager(mngr);
		
		projectRepository.save(project);
		
		ProjectPostDTO ppDto = new  ProjectPostDTO();
		ppDto.setName(project.getName());
		ppDto.setType(project.getType());
		ppDto.setManagerId(mngr.getId());
		ppDto.setStartDate(startDate);
		ppDto.setEndDate(endDate);
		ppDto.setEmpId(mngr.getEmployeeId());
		ppDto.setClientId(mngr.getClient().getId());
		
		return ppDto;
//		Manager mngr = managerRepository.save(manager);
//		Project project=projectRepository.findById(projectId).orElse(null);
//		project.setManager(mngr);
//
//		String clientid = project.getClient().getId();
//		String employeeid = project.getManager().getEmployeeId();
//		String type = project.getType();
//		LocalDateTime startDate = LocalDateTime.now();
//		LocalDateTime endDate =LocalDateTime.now();
//		
//		ProjectDTO prd = new ProjectDTO();
//		prd.setEmployeeId(employeeid);
//		prd.setName(project.getName());
//		prd.setType(mngr.getType());
//		prd.setStartDate(startDate);
//		prd.setEndDate(endDate);
//		projectRepository.save(project);
//
//		return null;
	}

	@Override
	public ProjectsDTO getProjectManager(String projectId) {
		Project project = projectRepository.findById(projectId).orElse(null);
		String employeeId = project.getManager().getEmployeeId();
		
		Client client = project.getClient();
		
		ProjectsDTO pDto = new ProjectsDTO();
		
		pDto.setId(project.getId());
		pDto.setClientId(client.getId());
		pDto.setManagerId(project.getManager().getId());
		pDto.setEmpId(employeeId);
		pDto.setEndDate(project.getEndDate());
		pDto.setStartDate(project.getStartDate());
		pDto.setName(project.getName());
		pDto.setType(project.getType());
		
		return pDto;
//		String clientId= project.getClient().getId();
//		String employeeId = project.getManager().getEmployeeId();
//		String type = project.getType();
//
//		ProjectDTO projectDto = new ProjectDTO();
//		projectDto.setType(type);
//	
//		projectDto.setClientDTOResponse(cdto);
//		
//		projectDto.setEmployeeId(employeeId);
//		projectDto.setStartDate(project.getStartDate());
//		projectDto.setEndDate(project.getEndDate());
//		projectDto.setName(project.getName());
//		
//		return projectDto;
	}

	@Override
	public ManagerDto updateProjectManager(String projectId, Manager manager) {
		Project project = projectRepository.findById(projectId).orElse(null);
		String cId = project.getClient().getId();
		
		Manager manager2 = project.getManager();
		
		manager2.setName(manager.getName());
		manager2.setType(manager.getType());
		manager2.setEmployeeId(manager.getEmployeeId());
		
		project.setManager(manager2);
		Project save = projectRepository.save(project);
		ManagerDto map = mapper.map(manager2, ManagerDto.class);
		map.setClientId(cId);
		
		return map;
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
