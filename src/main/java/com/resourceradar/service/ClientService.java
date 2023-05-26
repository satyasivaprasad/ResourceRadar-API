package com.resourceradar.service;

import java.util.List;

import com.resourceradar.dto.ClientDto;
import com.resourceradar.dto.ClientPDto;
import com.resourceradar.dto.ClientsDto;
import com.resourceradar.dto.ManagerDto;
import com.resourceradar.entity.Client;
import com.resourceradar.entity.Manager;

public interface ClientService {


       public ClientPDto createClientWithManger(String clientid,Manager manager);

       public ClientsDto getClientById(String id);
       
       public ClientDto updateClient(ClientDto updatedClientDto);
       
   	  public ClientDto updateClientById(String clientid, ClientDto client);

       public List<ClientsDto> getAllClients();
       
       public List<ClientDto> getAllClients(String clientid, Manager manager);
       
       public ClientDto CreateClient(ClientDto client);
       
       public ManagerDto updateClientManager(String clientId, Manager manager);       
       
       
       

}
