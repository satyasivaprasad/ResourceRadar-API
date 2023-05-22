package com.resourceradar.dto;

import java.time.LocalDateTime;

import com.resourceradar.entity.Manager;

import lombok.Data;

@Data
public class ProjectDTO {

    private String id;
    private String projectname;
    private String employeeId;
    private String type;
    private String clientId;
    private String orgId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Manager manager;

}
