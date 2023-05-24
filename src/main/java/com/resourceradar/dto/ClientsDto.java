package com.resourceradar.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ClientsDto {
	   	
	
	    private String managerId;
	    private String name;
	    private String status;
	  
	    private LocalDateTime startDate;
	    
	    private LocalDateTime endDate;
	    
	    private String managerName;

	    private  String type;

}
