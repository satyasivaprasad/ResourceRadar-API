package com.resourceradar.controller;

import java.util.ArrayList;
import java.util.List;

import com.resourceradar.dto.EmployeeDto;
import com.resourceradar.dto.EmployeeOrgRolesDto;
import com.resourceradar.entity.Employee;
import com.resourceradar.mapper.EmployeeMapper;
import com.resourceradar.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resourceradar.config.EndPointConfig;
import com.resourceradar.dto.ProjectDTO;
import com.resourceradar.dto.ProjectGetDTOResponse;
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

	@Autowired
	private EmployeeServiceImpl employeeService;

	@PostMapping()
    public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO) {
		ProjectDTO createdProject = projectService.createProject(projectDTO);
	    return createdProject;
	}
	
	@PutMapping("/{projectId}")
	public ProjectDTO updateProject(@PathVariable String projectId, @RequestBody ProjectDTO project) {
		ProjectDTO existingProject = projectService.updateProject(projectId,project);
	    return existingProject;
	}

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectGetDTOResponse> getProjectById(@PathVariable String projectId) {
    	ProjectGetDTOResponse project = projectService.getProjectById(projectId);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

	@GetMapping()
	public ResponseEntity<List<ProjectGetDTOResponse>> getAllProjects(){
        List<ProjectGetDTOResponse> projects = projectService.getAllProjects();
		if (projects.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(projects, HttpStatus.OK);
		}
	}

	@GetMapping("/managers")
	public List<EmployeeOrgRolesDto> getProjectManagers() {
		List<Employee> employees = employeeService.getAllEmployees();
		List<EmployeeOrgRolesDto> employeeOrgRolesDtos = new ArrayList<>();
		for (Employee employee : employees) {
			EmployeeDto employeeDto = EmployeeMapper.INSTANCE.mapToEmployeeDto(employee);
			employeeOrgRolesDtos.addAll(employeeDto.getRoles());
		}
		return employeeOrgRolesDtos;
	}

//	@PostMapping("/{projectId}/manager")
//	public ProjectPostDTO assignManagerToProject(@PathVariable String projectId, @RequestBody ManagerPostDTO managerDTO) {
//		ProjectPostDTO project = projectService.assignManagerToProject(projectId,managerDTO);
//	    return project;
//	}
//
//	
//	
//	@GetMapping("{projectId}/manager")
//	public projectGetDTOResponse getProjectManager(@PathVariable String projectId) {
//	    projectGetDTOResponse project = projectService.getProjectManager(projectId);
//	    
//	    return project;
//	}
//	
//	
//	@PutMapping("/{projectId}/manager")
//	public ManagerDto updateProjectManager(@PathVariable String projectId, @RequestBody ManagerPostDTO manager) {
//		ManagerDto updateProjectManager = projectService.updateProjectManager(projectId, manager);
//		return updateProjectManager;
//	}
//	
//	
//	public ResponseEntity<Page<Project>> getAllProjects(
//			@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "10") int size
//	) throws ProjectNotFoundException {
//		Pageable pageable = PageRequest.of(page, size);
//		Page<Project> projects = projectService.getAllProjects(pageable);
//
//		if (projects.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} else {
//			return new ResponseEntity<>(projects, HttpStatus.OK);
//		}
//	}
}
