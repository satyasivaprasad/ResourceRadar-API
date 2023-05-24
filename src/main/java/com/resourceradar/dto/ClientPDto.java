package com.resourceradar.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ClientPDto {
	
	private String name;
    private String status;
  
    private LocalDateTime startDate;
    
    private LocalDateTime endDate;
    
    private String managerName;

    private  String type;

}
