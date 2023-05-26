package com.resourceradar.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ClientDto {

    
    private String name;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    private ManagerDto managerDto;

}
