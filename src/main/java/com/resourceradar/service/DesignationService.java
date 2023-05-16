package com.resourceradar.service;

import java.util.List;

import com.resourceradar.entity.Designation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.resourceradar.exception.DesignationNotFoundException;

@Service
public interface DesignationService {

	Page<Designation> getAllDesignation(Pageable pageable) throws DesignationNotFoundException;

	public List<Designation> getAllDesignation() throws DesignationNotFoundException;

	List<Designation> getDesignationListBasedOnName(String name);



}
