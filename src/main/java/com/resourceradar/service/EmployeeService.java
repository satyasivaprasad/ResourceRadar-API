package com.resourceradar.service;

import java.util.List;

import com.resourceradar.dto.EmployeeDto;
import com.resourceradar.entity.Employee;

import jakarta.servlet.http.HttpServletRequest;

public interface EmployeeService {

          public Employee createEmployee(EmployeeDto employeeDTO, HttpServletRequest request);
          List<Employee> getAllEmployees();
          Employee getEmployeeById(String id);
          List<Employee> searchEmployee(String query);
}
