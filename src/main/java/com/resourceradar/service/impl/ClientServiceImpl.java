package com.resourceradar.service.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.ClientPDto;
import com.resourceradar.dto.ClientDto;
import com.resourceradar.dto.ClientsDto;
import com.resourceradar.dto.ManagerDto;
import com.resourceradar.entity.Client;
import com.resourceradar.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.ProjectDTO;
import com.resourceradar.entity.Project;
import com.resourceradar.enums.ClientStatus;
import com.resourceradar.repository.ClientRepository;
import com.resourceradar.repository.ManagerRepository;
import com.resourceradar.service.ClientService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	 @Autowired
	private ManagerRepository managerRepository;
	 

	 @Autowired
	 private ModelMapper mapper;
	//add client
	
	@Override
	public Client addClient(Client client) {
		// TODO Auto-generated method stub
		Client client2 = clientRepository.save(client);
		return client2;
	}

	//add manager to client
	@Override
	public ClientPDto createClientWithManger(String clientid, Manager manager) {
		// TODO Auto-generated method stub
		Client c = clientRepository.findById(clientid).orElse(null);
		
		Manager manager2 = managerRepository.save(manager);
		c.setManager(manager2);
		
		clientRepository.save(c);
		
		ClientPDto cl=new ClientPDto();
		cl.setEndDate(c.getEndDate());
		cl.setStartDate(c.getStartDate());
		cl.setManagerName(manager2.getName());
		cl.setName(c.getName());
		cl.setStatus(c.getStatus());
		cl.setType(manager2.getType());
		return cl;

	}

//	@Override
//	public Client createClient(ClientDto clientDTO, HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	@Override
	public ClientsDto getClientById(String id){
		// TODO Auto-generated method stub
		Client c = clientRepository.findById(id).orElse(null);
		Manager manager2 = c.getManager();
		
		
		
		ClientsDto cl=new ClientsDto();
		cl.setManagerId(manager2.getId());
		cl.setEndDate(c.getEndDate());
		cl.setStartDate(c.getStartDate());
		cl.setManagerName(manager2.getName());
		cl.setName(c.getName());
		cl.setStatus(c.getStatus());
		cl.setType(manager2.getType());
		
		
		return cl;

	}

	@Override
	public Client updateClientById(String id, Client client) {
		Client client2 = clientRepository.findById(id).orElse(null);
		
		client2.setName(client.getName());
		client2.setStatus(client.getStatus());
		clientRepository.save(client2);
		return client2;
		
		
	}

	@Override
	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		List<Client> findAll = clientRepository.findAll();
		return findAll;
	}
	
	@Override
	public ManagerDto updateClientManager(String clientId, Manager manager) {
			 Client client = clientRepository.findById(clientId).orElse(null);

			 Manager manager2 = client.getManager();
			
			 manager2.setName(manager.getName());
			 manager2.setType(manager.getType());
			 
			 client.setManager(manager2);
			 clientRepository.save(client);
			 ManagerDto map = mapper.map(manager2, ManagerDto.class);
			 return map;
		}
	}
		
	
