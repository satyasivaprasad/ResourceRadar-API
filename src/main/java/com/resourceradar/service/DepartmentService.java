package com.resourceradar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.resourceradar.entity.Departments;
import com.resourceradar.exception.DepartmentNotFoundException;

@Service
public interface DepartmentService {

	public List<Departments> getAllDepartments() throws DepartmentNotFoundException;

	public List<Departments> getDepartmentListBasedOnName(String name);
}