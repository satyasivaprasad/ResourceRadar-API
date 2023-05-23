package com.resourceradar.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ManagerDto {

   private String id;

    private  String type;
    
    private  String employee;
    
    private  String client;
    
    private  String projectId; 
    
    private  String orgId;
    
    private String createdBy;

    private String modifiedBy;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
    
    private String name;
    
    


}
