package com.resourceradar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resourceradar.config.EndPointConfig;
import com.resourceradar.dto.ClientDto;
import com.resourceradar.dto.ClientPDto;
import com.resourceradar.dto.ClientsDto;
import com.resourceradar.dto.ManagerDto;
import com.resourceradar.entity.Client;
import com.resourceradar.entity.Manager;
import com.resourceradar.service.impl.ClientServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(EndPointConfig.API_V1 + EndPointConfig.CLIENT)
@Tag(name = "client")
@Slf4j
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @PostMapping()
    public ClientDto CreateClient(@RequestBody ClientDto client) {
    	ClientDto client2 = clientService.CreateClient(client);
    	return client2;
    }

   

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientsDto> getClientById(@PathVariable String clientId) {
        try {
            ClientsDto clientDTO = clientService.getClientById(clientId);
            if (clientDTO == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(clientDTO);
        } catch (Exception e) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{clientId}")
    public ClientDto updateClientStatusById( @PathVariable String clientId,@RequestBody ClientDto clientDTO) {
        ClientDto updateClientById = clientService.updateClientById(clientId, clientDTO);
        log.info(clientDTO.getStatus() + "    " + clientId);
        return updateClientById;
    }
    
    
    @GetMapping()
    public List<ClientsDto> getAllClients(){

    	List<ClientsDto> allClients = clientService.getAllClients();
    	return allClients;
    }
    
    @PostMapping("/{clientid}/manager")
    public ClientPDto createManagerToClient(@PathVariable String clientid, @RequestBody Manager manager) {
    	ClientPDto createClientWithManger = clientService.createClientWithManger(clientid, manager);
    	return createClientWithManger;
    	
    }
    
    @PutMapping("/{clientId}/manager")
    public ManagerDto updateManager(@PathVariable String clientId,@RequestBody Manager manager) {
    	ManagerDto updateClientManager = clientService.updateClientManager(clientId, manager);
    	return updateClientManager;
    }

}
