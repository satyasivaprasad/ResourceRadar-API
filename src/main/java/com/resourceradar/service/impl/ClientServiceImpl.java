package com.resourceradar.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.ClientDto;
import com.resourceradar.dto.ClientPDto;
import com.resourceradar.dto.ClientsDto;
import com.resourceradar.dto.ManagerDto;
import com.resourceradar.entity.Client;
import com.resourceradar.entity.Manager;
import com.resourceradar.repository.ClientRepository;
import com.resourceradar.repository.ManagerRepository;
import com.resourceradar.service.ClientService;

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
	public ClientDto CreateClient(ClientDto clientdto) {
		Client map = mapper.map(clientdto, Client.class);
		Client client = clientRepository.save(map);
		ManagerDto managerDto = clientdto.getManagerDto();
		Manager manager = mapper.map(managerDto, Manager.class);
		manager.setClient(client);
		Manager manager2 = managerRepository.save(manager);
		ManagerDto managerDto2 = mapper.map(manager2, ManagerDto.class);
		ClientDto clientDto2 = mapper.map(client, ClientDto.class);
		clientDto2.setManagerDto(managerDto2);
		return clientDto2;
	}
	
	@Override
	public ClientDto updateClientById(String clientid, ClientDto client) {
		Client client2 = clientRepository.findById(clientid).orElse(null);
		
		client2.setName(client.getName());
		client2.setStatus(client.getStatus());
		client2.setStartDate(client.getStartDate());
		client2.setEndDate(client.getEndDate());
		
		Client cliententity = clientRepository.save(client2);
		Manager man = managerRepository.findbyClientId(clientid);
		man.setName(client.getManagerDto().getName());
		
		Manager man2 = managerRepository.save(man);
		ClientDto clientDto2 = mapper.map(client2, ClientDto.class);
		clientDto2.setManagerDto(mapper.map(man2, ManagerDto.class));
		return clientDto2;
	}

	//Create manager using clientId
	@Override
	public ClientPDto createClientWithManger(String clientid, Manager manager) {
		Client c = clientRepository.findById(clientid).orElse(null);
		
		Manager manager2 = managerRepository.save(manager);
		
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

	@Override
	public ClientsDto getClientById(String id){
		Optional<Client> c = clientRepository.findById(id);
		Client client = c.get();
		
		ClientsDto dto = new ClientsDto();
		dto.setId(client.getId());
		dto.setName(client.getName());
		dto.setStatus(client.getStatus());
		dto.setStartDate(client.getStartDate());
		dto.setEndDate(client.getEndDate());
		Manager man = managerRepository.findbyClientId(client.getId());
		dto.setManagerId(man.getId());
		dto.setManagerName(man.getName());
			
		
		ClientsDto cl=new ClientsDto();
		return dto;
	}

	@Override
	public List<ClientsDto> getAllClients() {
		List<Client> findAll = clientRepository.findAll();
		List<ClientsDto> clientsDtos = new ArrayList<ClientsDto>();
		for (Client client : findAll) {
			ClientsDto clientsDto = new ClientsDto();
			clientsDto.setId(client.getId());
			clientsDto.setName(client.getName());
			clientsDto.setStatus(client.getStatus());
			clientsDto.setStartDate(client.getStartDate());
			clientsDto.setEndDate(client.getEndDate());
			clientsDto.setManagerId(client.getId());
			clientsDto.setManagerName(client.getName());
			
			clientsDtos.add(clientsDto);
			
			
		}
		
		return clientsDtos;
	}		
}	
