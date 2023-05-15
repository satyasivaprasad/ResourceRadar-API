package com.resourceradar.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
	
	private String id;

	private String name;

	private String status;

	private String orgId;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private List<ProjectDTO> projects;

	private ManagerDTO managerDTO;

}