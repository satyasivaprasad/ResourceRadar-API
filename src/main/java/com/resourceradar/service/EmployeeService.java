package com.resourceradar.service;

import com.resourceradar.dto.EmployeeDTO;
import com.resourceradar.entity.Employee;
import jakarta.servlet.http.HttpServletRequest;

public interface EmployeeService {

          public Employee createEmployee(EmployeeDTO employeeDTO, HttpServletRequest request);
}
