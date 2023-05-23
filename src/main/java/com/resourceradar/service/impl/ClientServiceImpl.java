package com.resourceradar.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.ClientDto;
import com.resourceradar.entity.Client;
import com.resourceradar.entity.Manager;
import com.resourceradar.exception.ClientNotFoundException;
import com.resourceradar.repository.ClientRepository;
import com.resourceradar.repository.ManagerRepository;
import com.resourceradar.service.ClientService;


@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	 @Autowired
	 private ManagerRepository managerRepository;
	 
	    @Override
	    public Client createClient(Client client) {
	        return clientRepository.save(client);
	    }

	    @Override
	    public Client updateClient(Client client) {
	        return clientRepository.save(client);
	    }

	    @Override
	    public List<Client> getAllClients() {
	        return clientRepository.findAll();
	    }

	    @Override
	    public Client getClientById(String clientId) {
	        return clientRepository.findById(clientId).orElse(null);
	    }
	    @Override
	    public ClientDto assignManagerToClient(String clientId, Manager manager) {
	    	Manager m = managerRepository.save(manager);
	        Client client = clientRepository.findById(clientId).orElse(null);
	        client.setManager(m);
	        
	        String name = client.getName();
	        String status = client.getStatus();
	        String orgid = client.getOrgId();
	        LocalDateTime startDate = LocalDateTime.now();
	        LocalDateTime endDate =LocalDateTime.now();
	        
	        ClientDto dto = new ClientDto();
	        dto.setName(client.getName());
	        dto.setStatus(status);
	        dto.setOrgId(orgid);
	        dto.setStartDate(startDate);
	        dto.setEndDate(endDate);
	
	        	client.setManager(manager);
	            clientRepository.save(client);
	        
	        return dto;
	    }
	    
	    @Override
	    public ClientDto getClientManager(String clientId) {
	    	Client client= clientRepository.findById(clientId).orElse(null);
	    	
	    	String name = client.getName();
	        String status = client.getStatus();
	        String orgid = client.getOrgId();
//	        String employeeid = client.getManager().getEmployeeid();
	        
	        ClientDto clientDto = new ClientDto();
	        clientDto.setName(client.getName());
	        clientDto.setOrgId(client.getOrgId());
	        clientDto.setStatus(status);
	        clientDto.setStartDate(client.getStartDate());
	        clientDto.setEndDate(client.getEndDate());
	        clientDto.setManager(client.getManager());
	      
	       return clientDto;
	    }
	        
	    @Override
	    public Client updateClientManager(String clientId, Manager manager) {
	    	Client client = clientRepository.findById(clientId).orElse(null);
	        if (client != null) {
	        	client.setManager(manager);
	            return clientRepository.save(client);
	        }
	        return null;
	    }

//		@Override
//		public Page<Client> getAllClient(Pageable pageable) throws ClientNotFoundException {
//			// TODO Auto-generated method stub
//			return null;
//		}
}

//		@Override
//		public Page<Client> getAllClient(org.springframework.data.domain.Pageable pageable)
//				throws ClientNotFoundException {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	    
		






