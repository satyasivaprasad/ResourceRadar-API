package com.resourceradar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.ClientDto;
import com.resourceradar.entity.Client;
import com.resourceradar.entity.Manager;
import com.resourceradar.exception.ClientNotFoundException;

@Service
public interface ClientService {
	
	public Client createClient(Client client);
	
	public Client updateClient(Client client);
	
	public List<Client> getAllClients();
	
	public Client getClientById(String clientId);
	
	public ClientDto assignManagerToClient(String clientId, Manager manager);
	
	public ClientDto getClientManager(String clientId);
	
	public Client updateClientManager(String clientId, Manager manager);
	
//    Page<Client> getAllClient(Pageable pageable) throws ClientNotFoundException ;
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*
 * 

       public Client createClientWithManger(ClientDto clientDTO, Manager manager);
       public Client createClient(ClientDto clientDTO, HttpServletRequest request);

       public ClientDto getClientById(String id, HttpServletRequest request) throws Exception;

       public String updateClientById(String id, String status);

       public List<Client> getAllClients();
       
       */
	

}
