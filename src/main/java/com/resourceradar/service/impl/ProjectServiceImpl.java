package com.resourceradar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Project assignManagerToProject(String projectId, Manager manager) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            project.setManager(manager);
            return projectRepository.save(project);
        }
        return null;
    }
    @Override
    public Manager getProjectManager(String projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            return project.getManager();
        }
        return null;
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
		}
		catch (Exception e){
			throw new ProjectNotFoundException(" Project Details not found");
		}
	}
}
