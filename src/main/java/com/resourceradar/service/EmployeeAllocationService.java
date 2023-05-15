package com.resourceradar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.resourceradar.DTO.EmployeeAllocationDTO;
import com.resourceradar.entity.EmployeeAllocation;
import com.resourceradar.exception.EmployeeAllocationNotFoundException;

@Service
public interface EmployeeAllocationService {

	EmployeeAllocation createEmployeeAllocation(EmployeeAllocationDTO employeeAllocationDto);

	EmployeeAllocation updateEmployeeAllocation(EmployeeAllocation employeeAllocation)
			throws EmployeeAllocationNotFoundException;

	List<EmployeeAllocation> getAllEmployeeAllocations();

	EmployeeAllocation getEmployeeAllocationById(String id);

	String deleteEmployeeAllocation(String id) throws EmployeeAllocationNotFoundException;
}