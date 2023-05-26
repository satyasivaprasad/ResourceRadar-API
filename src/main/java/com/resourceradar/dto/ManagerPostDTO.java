package com.resourceradar.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ManagerPostDTO {

	private String name;

	private String type;
	
	private String employeeId;

	private String createdBy;

	private String modifiedBy;

	private LocalDateTime createdAt;

	private LocalDateTime modifiedAt;
	
//	private String clientId;
//	
//	private String orgId;

}
