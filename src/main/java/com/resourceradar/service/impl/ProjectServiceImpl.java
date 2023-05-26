package com.resourceradar.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.ManagerDto;
import com.resourceradar.dto.ManagerPostDTO;
import com.resourceradar.dto.ProjectDTO;
import com.resourceradar.dto.projectGetDTOResponse;
import com.resourceradar.entity.Client;
import com.resourceradar.entity.Manager;
import com.resourceradar.entity.Organization;
import com.resourceradar.entity.Project;
import com.resourceradar.repository.ClientRepository;
import com.resourceradar.repository.OrganizationRepository;
import com.resourceradar.repository.ProjectRepository;
import com.resourceradar.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Override
	public ProjectDTO createProject(ProjectDTO projectDTO) {
		Project project =  new Project();
		project.setName(projectDTO.getName());
		project.setType(projectDTO.getType());
		project.setOrgId(projectDTO.getOrgId());
		project.setStartDate(projectDTO.getStartDate());
		project.setEndDate(projectDTO.getEndDate());
		Optional<Client> client = clientRepository.findById(projectDTO.getClientId());
		project.setClient(client.get());
		
		ManagerPostDTO managerPostDTO = projectDTO.getManager();
		Manager manager = new Manager();
		manager.setName(managerPostDTO.getName());
		manager.setType(managerPostDTO.getType());
		manager.setEmployeeId(managerPostDTO.getEmployeeId());
		manager.setCreatedAt(managerPostDTO.getCreatedAt());
		manager.setModifiedAt(managerPostDTO.getModifiedAt());
		manager.setCreatedBy(managerPostDTO.getCreatedBy());
		manager.setModifiedBy(managerPostDTO.getModifiedBy());
		Optional<Client> cli = clientRepository.findById(projectDTO.getClientId());
		manager.setClient(cli.get());
		Optional<Organization> org = organizationRepository.findById(projectDTO.getOrgId());
		manager.setOrganization(org.get());
		
		
		project.setManager(manager);
		
		projectRepository.save(project);
			
		ProjectDTO dto = new ProjectDTO();
		dto.setName(project.getName());
		dto.setType(project.getType());
		dto.setOrgId(project.getOrgId());
		dto.setStartDate(project.getStartDate());
		dto.setEndDate(project.getEndDate());
		dto.setClientId(project.getClient().getId());
		dto.setManager(managerPostDTO);
		
		return dto;
	}

	@Override
	public ProjectDTO updateProject(String projectId, ProjectDTO projectDTO) {
		Optional<Project> pro = projectRepository.findById(projectId);
		Project project = pro.get();
		project.setName(projectDTO.getName());
		project.setType(projectDTO.getType());
		project.setOrgId(projectDTO.getOrgId());
		project.setStartDate(projectDTO.getStartDate());
		project.setEndDate(projectDTO.getEndDate());
		Optional<Client> client = clientRepository.findById(projectDTO.getClientId());
		project.setClient(client.get());
		
		ManagerPostDTO managerPostDTO = projectDTO.getManager();
		Manager manager = pro.get().getManager();
		manager.setName(managerPostDTO.getName());
		manager.setType(managerPostDTO.getType());
		manager.setEmployeeId(managerPostDTO.getEmployeeId());
		manager.setCreatedAt(managerPostDTO.getCreatedAt());
		manager.setModifiedAt(managerPostDTO.getModifiedAt());
		manager.setCreatedBy(managerPostDTO.getCreatedBy());
		manager.setModifiedBy(managerPostDTO.getModifiedBy());
//		Optional<Client> cli = clientRepository.findById(projectDTO.getClientId());
//		manager.setClient(cli.get());
//		Optional<Organization> org = organizationRepository.findById(projectDTO.getOrgId());
//		manager.setOrganization(org.get());
		project.setManager(manager);
		
		projectRepository.save(project);
		
		ProjectDTO dto = new ProjectDTO();
		dto.setName(project.getName());
		dto.setType(project.getType());
		dto.setOrgId(project.getOrgId());
		dto.setStartDate(project.getStartDate());
		dto.setEndDate(project.getEndDate());
		dto.setClientId(project.getClient().getId());
		dto.setManager(managerPostDTO);
		return dto;
	}

	@Override
	public List<projectGetDTOResponse> getAllProjects() {
		List<Project> findAllProjects = projectRepository.findAll();
		List<projectGetDTOResponse>  dtoResponses = new ArrayList<projectGetDTOResponse>();
		for (Project project : findAllProjects) {
			projectGetDTOResponse projectGetDTOResponse = new projectGetDTOResponse();	
			projectGetDTOResponse.setId(project.getId());
			projectGetDTOResponse.setName(project.getName());
			projectGetDTOResponse.setType(project.getType());
			projectGetDTOResponse.setStartDate(project.getStartDate());
			projectGetDTOResponse.setEndDate(project.getEndDate());
			projectGetDTOResponse.setClient(project.getClient());
			projectGetDTOResponse.setOrgId(project.getOrgId());
				
			ManagerDto dto = new ManagerDto();
			dto.setId(project.getManager().getId());
			dto.setEmployeeId(project.getManager().getEmployeeId());
			dto.setName(project.getManager().getName());
			dto.setType(project.getManager().getType());
			dto.setCreatedBy(project.getManager().getCreatedBy());
			dto.setModifiedBy(project.getManager().getModifiedBy());
			dto.setCreatedAt(project.getManager().getCreatedAt());
			dto.setModifiedAt(project.getManager().getModifiedAt());
			projectGetDTOResponse.setManager(dto);
			
			dtoResponses.add(projectGetDTOResponse);
		}
		
		return dtoResponses;
	}

	@Override
	public projectGetDTOResponse getProjectById(String projectId) {
		Project project = projectRepository.findById(projectId).orElse(null);
		projectGetDTOResponse projectGetDTOResponse = new projectGetDTOResponse();
		projectGetDTOResponse.setId(project.getId());
		projectGetDTOResponse.setName(project.getName());
		projectGetDTOResponse.setType(project.getType());
		projectGetDTOResponse.setStartDate(project.getStartDate());
		projectGetDTOResponse.setEndDate(project.getEndDate());
		projectGetDTOResponse.setClient(project.getClient());
		projectGetDTOResponse.setOrgId(project.getOrgId());
		ManagerDto dto = new ManagerDto();
		dto.setId(project.getManager().getId());
		dto.setEmployeeId(project.getManager().getEmployeeId());
		dto.setName(project.getManager().getName());
		dto.setType(project.getManager().getType());
		dto.setCreatedBy(project.getManager().getCreatedBy());
		dto.setModifiedBy(project.getManager().getModifiedBy());
		dto.setCreatedAt(project.getManager().getCreatedAt());
		dto.setModifiedAt(project.getManager().getModifiedAt());
		
		projectGetDTOResponse.setManager(dto);	
		
		return projectGetDTOResponse;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	@Override
//	public ProjectDTO createProject(ProjectDTO projectDTO) {
////		Project map = mapper.map(projectDTO, Project.class);
//		Project pro = new Project();
//		pro.setName(projectDTO.getName());
//		
//		Employee employee = employeeRepository.findById(projectDTO.getEmployeeId()).orElse(null);
//		pro.setType(projectDTO.getType());
//		pro.setStartDate(projectDTO.getStartDate());
//		pro.setEndDate(projectDTO.getEndDate());
//		
//		Client client = clientRepository.findById(projectDTO.getClientId()).orElse(null);
//		pro.setClient(client);
//		
//		projectRepository.save(pro);
//		ProjectDTO map2 = mapper.map(pro, projectDTO.getClass());
//		map2.setEmployeeId(projectDTO.getEmployeeId());
//		return map2;
//	}
//	@Override
//	public ProjectDTO updateProject(String projectId,ProjectDTO project) {
//		Project pro = projectRepository.findById(projectId).orElse(null);
//		
//		pro.setName(project.getName());
//		pro.setType(project.getType());
//		
//		Project save = projectRepository.save(pro);
//		ProjectDTO map = mapper.map(save, ProjectDTO.class);
//		return map;
//	}
//
//	@Override
//	public List<ProjectDTO> getAllProjects() {
//		 List<Project> findAllProjects = projectRepository.findAll();
//		 List<ProjectDTO> listProjectDTO = new ArrayList<ProjectDTO>();
//		 for (Project project : findAllProjects) {
//			ProjectDTO dto = new ProjectDTO();
//			dto.setClientId(project.getClient().getId());
//			dto.setEmployeeId(project.getManager().getEmployeeId());
//			dto.setEndDate(project.getEndDate());
//			dto.setName(project.getName());
//			dto.setStartDate(project.getStartDate());
//			dto.setType(project.getType());
//			listProjectDTO.add(dto);
//		}
//		return listProjectDTO; 
//	}
//
//	@Override
//	public ProjectsDTO getProjectById(String projectId) {
//		Project project = projectRepository.findById(projectId).orElse(null);
//		ProjectsDTO map = mapper.map(project, ProjectsDTO.class);
//		return map;
//	}
//
//	@Override
//	public ProjectPostDTO assignManagerToProject(String projectId, ManagerPostDTO managerDTO) {
//		Manager mapManager = mapper.map(managerDTO, Manager.class);
//		
//		Manager mngr = managerRepository.save(mapManager);
//		Project project = projectRepository.findById(projectId).orElse(null);
//		
//		LocalDateTime startDate = LocalDateTime.now();
//		LocalDateTime endDate =LocalDateTime.now();
//		
//		project.setManager(mngr);
//		
//		projectRepository.save(project);
//		
//		ProjectPostDTO ppDto = new  ProjectPostDTO();
//		ppDto.setName(project.getName());
//		ppDto.setType(project.getType());
//		ppDto.setManagerId(mngr.getId());
//		ppDto.setStartDate(startDate);
//		ppDto.setEndDate(endDate);
//		ppDto.setEmpId(mngr.getEmployeeId());
//		ppDto.setClientId(mngr.getClient().getId());
//		
//		return ppDto;
	
////		Manager mngr = managerRepository.save(manager);
////		Project project=projectRepository.findById(projectId).orElse(null);
////		project.setManager(mngr);
////
////		String clientid = project.getClient().getId();
////		String employeeid = project.getManager().getEmployeeId();
////		String type = project.getType();
////		LocalDateTime startDate = LocalDateTime.now();
////		LocalDateTime endDate =LocalDateTime.now();
////		
////		ProjectDTO prd = new ProjectDTO();
////		prd.setEmployeeId(employeeid);
////		prd.setName(project.getName());
////		prd.setType(mngr.getType());
////		prd.setStartDate(startDate);
////		prd.setEndDate(endDate);
////		projectRepository.save(project);
////
////		return null;
//	}
//
//	@Override
//	public ProjectsDTO getProjectManager(String projectId) {
//		Project project = projectRepository.findById(projectId).orElse(null);
//		String employeeId = project.getManager().getEmployeeId();
//		
//		Client client = project.getClient();
//		
//		ProjectsDTO pDto = new ProjectsDTO();
//		
//		pDto.setId(project.getId());
//		pDto.setClientId(client.getId());
//		pDto.setManagerId(project.getManager().getId());
//		pDto.setEmpId(employeeId);
//		pDto.setEndDate(project.getEndDate());
//		pDto.setStartDate(project.getStartDate());
//		pDto.setName(project.getName());
//		pDto.setType(project.getType());
//		
//		return pDto;
////		String clientId= project.getClient().getId();
////		String employeeId = project.getManager().getEmployeeId();
////		String type = project.getType();
////
////		ProjectDTO projectDto = new ProjectDTO();
////		projectDto.setType(type);
////	
////		projectDto.setClientDTOResponse(cdto);
////		
////		projectDto.setEmployeeId(employeeId);
////		projectDto.setStartDate(project.getStartDate());
////		projectDto.setEndDate(project.getEndDate());
////		projectDto.setName(project.getName());
////		
////		return projectDto;
//	}
//
//	@Override
//	public ManagerDto updateProjectManager(String projectId, ManagerPostDTO manager) {
//		Project project = projectRepository.findById(projectId).orElse(null);
//		String cId = project.getClient().getId();
//		
//		Manager manager2 = project.getManager();
//		
//		manager2.setName(manager.getName());
//		manager2.setType(manager.getType());
//		manager2.setEmployeeId(manager.getEmployeeId());
//		
//		Manager mapManager = mapper.map(manager, Manager.class);
//		
//		project.setManager(mapManager);
//		projectRepository.save(project);
//		ManagerDto map = mapper.map(manager2, ManagerDto.class);
//		map.setClientId(cId);
//		
//		return map;
//	}
//
//	@Override
//	public Page<Project> getAllProjects(Pageable pageable) throws ProjectNotFoundException {
//		try {
//			return projectRepository.findAll(pageable);
//		} catch (Exception e) {
//			throw new ProjectNotFoundException(" Project Details not found");
//		}
//	}
}
