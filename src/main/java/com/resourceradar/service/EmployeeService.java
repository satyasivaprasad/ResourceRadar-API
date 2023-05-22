package com.resourceradar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resourceradar.dto.EmployeeDto;
import com.resourceradar.entity.Employee;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface EmployeeService {

          public Employee createEmployee(EmployeeDto employeeDTO, HttpServletRequest request);
          List<Employee> getAllEmployees();
          Optional<Employee> getEmployeeById(String id);
          List<Employee> searchEmployee(String firstname,String lastname);
}
