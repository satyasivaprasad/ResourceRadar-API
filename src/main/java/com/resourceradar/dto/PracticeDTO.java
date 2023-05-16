package com.resourceradar.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PracticeDTO {

	private String name;
	private String type;
	private String orgId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;



}
