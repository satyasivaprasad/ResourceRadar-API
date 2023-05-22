package com.resourceradar.service;

import com.resourceradar.dto.EmployeeDto;
import com.resourceradar.entity.Employee;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee createEmployee(EmployeeDto employeeDTO, HttpServletRequest request);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(String id);

    List<Employee> searchEmployee(String firstname, String lastname);

    Employee getEmployeeByEmailId(String id);
}
