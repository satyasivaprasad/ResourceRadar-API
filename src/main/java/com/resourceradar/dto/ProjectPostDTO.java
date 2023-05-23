package com.resourceradar.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProjectPostDTO {
	
	private String name;
	private String EmpId;
	private String type;
	private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String clientId;
    private String managerId;

}
