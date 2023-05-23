package com.resourceradar.dto;

import java.time.LocalDateTime;

import com.resourceradar.entity.Manager;

import lombok.Data;

@Data
public class ClientDto {

    private String id;
    private String name;
    private String status;
    private String orgId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
//  private List<ProjectDTO> projects;
    private Manager manager;

}
