package com.resourceradar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resourceradar.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

	List<Department> findByNameContainingIgnoreCase(String name);
	Page<Departments> findByNameContainingIgnoreCase(String name, Pageable pageable);

	@Override
	public Page<Departments> getAllDepartments(Pageable pageable) throws DepartmentNotFoundException {
		return departmentRepository.findAll(pageable);
	}

	@Override
	public Page<Departments> getDepartmentListBasedOnName(String name, Pageable pageable) {
		return departmentRepository.findByNameContainingIgnoreCase(name, pageable);
	}

	@Override
	public Page<Departments> getDepartmentsByName(String name, Pageable pageable) {
		return departmentRepository.findByNameContainingIgnoreCase(name, pageable);
	}

}
