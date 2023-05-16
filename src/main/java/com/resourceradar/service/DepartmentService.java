package com.resourceradar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.resourceradar.entity.Departments;
import com.resourceradar.exception.DepartmentNotFoundException;

@Service
public interface DepartmentService {

	public List<Departments> getAllDepartments() throws DepartmentNotFoundException;

	public List<Departments> getDepartmentListBasedOnName(String name);

	Page<Departments> getAllDepartments(Pageable pageable) throws DepartmentNotFoundException;

	Page<Departments> getDepartmentListBasedOnName(String name, Pageable pageable);

	Page<Departments> getDepartmentsByName(String name, Pageable pageable);
}