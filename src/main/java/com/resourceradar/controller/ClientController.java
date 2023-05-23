package com.resourceradar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.resourceradar.config.EndPointConfig;
import com.resourceradar.dto.ClientDto;
import com.resourceradar.entity.Client;
import com.resourceradar.entity.Manager;
import com.resourceradar.exception.ClientNotFoundException;
import com.resourceradar.repository.ClientRepository;
import com.resourceradar.repository.ManagerRepository;
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
      
//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
    
    @PostMapping
    public String createClient(@RequestBody Client client) {
    	
        Client createdClient = clientService.createClient(client);
        
        return "client details saved successfully. client ID: " + createdClient.getId();
    }
    
    
    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable String clientId, @RequestBody Client client) {
    	
    	Client existingClient = clientService.getClientById(clientId);
    	
        if (existingClient == null) {
            return ResponseEntity.notFound().build();
        }
        
        client.setId(clientId); 
        Client updatedClient = clientService.updateClient(client);
        
        return ResponseEntity.ok(updatedClient);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable String clientId) {
    	
    	Client client = clientService.getClientById(clientId);
    	
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Client>> getAllClient() throws ClientNotFoundException {
    	
        List<Client> clientList = clientService.getAllClients();
        
        if (clientList.isEmpty()){
        	
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
        	
            return new ResponseEntity<>(clientList, HttpStatus.OK);
        }
    }
    
   @PostMapping("/{clientId}/manager")
   public ClientDto assignManagerToClient(@PathVariable String clientId, @RequestBody Manager manager) {
    	ClientDto client = clientService.assignManagerToClient(clientId, manager);    
           return client;    
   }
   
   @GetMapping("/{clientId}/manager")
   public ClientDto getClientManager(@PathVariable String clientId) {
	   	ClientDto client = clientService.getClientManager(clientId);
	    return client;
    }
    
    @PutMapping("/{clientId}/manager")
    public Client updateClientManager(@PathVariable String clientId, @RequestBody Manager manager) {
    	Client client = clientService.getClientById(clientId);
    	
        if (client != null) {
        	client.setManager(manager);
        	return clientService.updateClient(client);
        
        }
        return null;
    }
    
    
//    public ResponseEntity<Page<Client>> getAllClients(
//    		@RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) throws ClientNotFoundException {
//    	
////        Pageable pageable = PageRequest.of(page, size);
//        Page<Client> clients = clientService.getAllClient(pageable);
//
//        if (clients.isEmpty()) {
//        	
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            
//        } else {
//        	
//            return new ResponseEntity<>(clients, HttpStatus.OK);
//            
//       }
    }


    
    
    
    
    
    
 