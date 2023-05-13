package com.resourceradar.service;

import java.util.List;

import com.resourceradar.entity.Organization;
import org.springframework.stereotype.Service;

import com.resourceradar.DTO.OrganizationDTO;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface OrganizationService {

	public List<Organization> getAllOrganization();
	
	public OrganizationDTO getOrgCompleteDetails(HttpServletRequest request);

}
