package com.resourceradar.service;

import com.resourceradar.dto.EmployeeDto;
import com.resourceradar.entity.Employee;
import jakarta.servlet.http.HttpServletRequest;

public interface EmployeeService {

          public Employee createEmployee(EmployeeDto employeeDTO, HttpServletRequest request);
}
