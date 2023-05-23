package com.resourceradar.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProjectsDTO {
	
	private String id;
	private String name;
	private String EmpId;
	private String type;
	private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String clientId;
    private String managerId;
}
