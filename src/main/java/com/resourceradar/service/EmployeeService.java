package com.resourceradar.service;

import com.resourceradar.dto.EmployeeDto;
import com.resourceradar.entity.Employee;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(EmployeeDto employeeDTO, HttpServletRequest request);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(String id);

    List<Employee> searchEmployee(String query);

    Employee getEmployeeByEmailId(String id);
}
