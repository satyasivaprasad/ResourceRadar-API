package com.resourceradar.service;

import java.util.List;

import com.resourceradar.dto.ClientDto;
import com.resourceradar.entity.Client;
import jakarta.servlet.http.HttpServletRequest;

public interface ClientService {


       public Client createClientWithManger(ClientDto clientDTO, HttpServletRequest request);
       public Client createClient(ClientDto clientDTO, HttpServletRequest request);

       public ClientDto getClientById(String id, HttpServletRequest request) throws Exception;

       public String updateClientById(String id, String status);


       public List<Client> getAllClients();

}
