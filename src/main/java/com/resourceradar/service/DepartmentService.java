package com.resourceradar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.resourceradar.entity.Department;
import com.resourceradar.exception.DepartmentNotFoundException;

@Service
public interface DepartmentService {

	public List<Department> getAllDepartments() throws DepartmentNotFoundException;

	public List<Department> getDepartmentListBasedOnName(String name);
}