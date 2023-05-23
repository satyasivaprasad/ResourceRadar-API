package com.resourceradar.service.impl;

import com.resourceradar.entity.Client;
import com.resourceradar.entity.Project;
import com.resourceradar.exception.ProjectNotFoundException;
import com.resourceradar.repository.ClientRepository;
import com.resourceradar.repository.ProjectRepository;
import com.resourceradar.service.ProjectService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<Project> findAllProjectsBYClientId(String clientId) {
		Optional<Client> client = clientRepository.findById(clientId);
	//	List<Project> projects = client.get().getProjects();
		return null;
	}
	@Override
	public List<Project> getAllProjects() throws ProjectNotFoundException {
		try {
			List<Project> projects = projectRepository.findAll();
			return projects;
		}
		catch (Exception e){
			throw new ProjectNotFoundException(" Project Details not found");
		}
	}
	@Override
	public List<Project> getProjectBasedOnClineId(String clientId) {
		Optional<Client> client = clientRepository.findById(clientId);
		//List<Project> projects = client.get().getProjects();
		return null;
	}
	@Override
	public List<Project> getProjectBasedOnClientId(String clientId) {
		Optional<Client> client = clientRepository.findById(clientId);
//		List<Project> projects = client.get().getProjects();
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
