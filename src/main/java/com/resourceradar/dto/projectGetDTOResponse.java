package com.resourceradar.dto;

import java.time.LocalDateTime;

import com.resourceradar.entity.Client;
import com.resourceradar.entity.Manager;

import lombok.Data;

@Data
public class projectGetDTOResponse {

	private String id;
	private String name;
	private String type;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String orgId;
	private Client client;
	private ManagerDto manager;
}
