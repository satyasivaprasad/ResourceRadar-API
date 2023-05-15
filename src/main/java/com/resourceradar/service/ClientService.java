package com.resourceradar.service;

import org.springframework.stereotype.Service;

import com.resourceradar.DTO.ClientDTO;
import com.resourceradar.entity.Client;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface ClientService {

	public Client createClientWithManger(ClientDTO clientDTO, HttpServletRequest request);

	public Client createClient(ClientDTO clientDTO, HttpServletRequest request);

	public ClientDTO getClientById(String id) throws Exception;

	public String updateClientById(String id, String status);

}
