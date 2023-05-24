package com.resourceradar.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProjectDTO {
	
	private String id;
    private String name;
    private String employeeId;
    private String type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String  orgId;

}
