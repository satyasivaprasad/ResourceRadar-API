package com.resourceradar.repository;

import com.resourceradar.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Employee findByOrgEmpId(String orgEmpId);
}
