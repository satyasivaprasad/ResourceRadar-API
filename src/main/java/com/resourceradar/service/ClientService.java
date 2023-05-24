package com.resourceradar.service;

import java.util.List;

import com.resourceradar.dto.ClientPDto;
import com.resourceradar.dto.ClientsDto;
import com.resourceradar.dto.ManagerDto;
import com.resourceradar.entity.Client;
import com.resourceradar.entity.Manager;

public interface ClientService {


       public ClientPDto createClientWithManger(String clientid,Manager manager);
//       public Client createClient(ClientDto clientDTO, HttpServletRequest request);

       public ClientsDto getClientById(String id);

       public Client updateClientById(String id,Client client);

       public List<Client> getAllClients();
       
       public Client addClient(Client client);
       
       public ManagerDto updateClientManager(String clientId, Manager manager);       
       
       
       

}
