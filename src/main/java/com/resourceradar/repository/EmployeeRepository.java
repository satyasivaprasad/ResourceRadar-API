package com.resourceradar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.resourceradar.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Employee findByOrgEmpId(String orgEmpId);

    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstNameQuery, String lastNameQuery);

    @Query(value = "select * from employees where email=?1",nativeQuery = true)
    Employee findByEmail(String email);
}
