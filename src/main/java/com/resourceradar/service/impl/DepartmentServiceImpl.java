package com.resourceradar.service.impl;

import java.util.List;

import com.resourceradar.repository.DepartmentRepository;
import com.resourceradar.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourceradar.entity.Department;
import com.resourceradar.exception.DepartmentNotFoundException;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Department> getAllDepartments() throws DepartmentNotFoundException {

		try {
			List<Department> departments = departmentRepository.findAll();
			return departments;
		} catch (Exception e) {
			throw new DepartmentNotFoundException(" Department Details not found");
		}
	}

	@Override
	public List<Department> getDepartmentListBasedOnName(String name) {

		List<Department> departments = departmentRepository.findByNameContainingIgnoreCase(name);
		return departments;

	}

	
}