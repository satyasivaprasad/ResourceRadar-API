package com.resourceradar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.resourceradar.config.EndPointConfig;
import com.resourceradar.dto.EmployeeDto;
import com.resourceradar.entity.Employee;
import com.resourceradar.exception.CustomValidationException;
import com.resourceradar.exception.EmployeeNotFoundException;
import com.resourceradar.mapper.EmployeeMapper;
import com.resourceradar.repository.EmployeeAuditRepository;
import com.resourceradar.repository.EmployeeRepository;
import com.resourceradar.service.impl.EmployeeServiceImpl;
import com.resourceradar.utils.Validator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndPointConfig.API_V1 + EndPointConfig.EMPLOYEE)
@Tag(name = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeAuditRepository employeeAuditRepository;

    @PostMapping()
    public String createEmployee(@RequestBody @Validated EmployeeDto employeeDTO, HttpServletRequest request)
            throws JsonProcessingException {
        try {
            Validator.isValidate(employeeDTO);
            Employee employee = employeeRepository.findByOrgEmpId(employeeDTO.getOrgEmpId());
            Employee employeeMap = EmployeeMapper.INSTANCE.mapEmployee(employeeDTO);
            if (employee == null) {
                Employee emp = employeeService.createEmployee(employeeDTO, request);
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());

                /*
                 * String jsonData = mapper.writeValueAsString(emp); EmployeeAudit employeeAudit
                 * = new EmployeeAudit(); employeeAudit.setData(jsonData);
                 * employeeAudit.setEmpId(emp.getOrgEmpId());
                 * employeeAudit.setEventType(String.valueOf(EmployeeAuditEvent.ADD));
                 * employeeAudit.setOrgId(request.getHeader("FL"));
                 * employeeAudit.setEventDate(LocalDateTime.now());
                 * employeeAuditRepository.save(employeeAudit);
                 *
                 */

            } else {
                return "employee org id already exists " + employee.getOrgEmpId();
            }
        } catch (CustomValidationException e) {
            throw new RuntimeException(e);
        }

        return "employee created successfully";

    }

    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(EndPointConfig.ID)
    public ResponseEntity<Employee> getEmployeeByOrgId(@PathVariable String orgId) {
        Employee employee = employeeService.getEmployeeById(orgId);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping(EndPointConfig.EMAIL_ID)
    public ResponseEntity<Employee> getEmployeeByEmailID(@RequestParam("query") String query) {
        Employee employee = employeeService.getEmployeeByEmailId(query);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping(EndPointConfig.EMPLOYEE_SEARCH)
    public ResponseEntity<List<Employee>> searchEmployee(@RequestParam("query") String query) throws EmployeeNotFoundException {
        List<Employee> employees = employeeService.searchEmployee(query);

        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
    }

    @DeleteMapping(EndPointConfig.ID)
    public String deleteEmployeeAllocation(@PathVariable("id") String id) {

        employeeService.findemployee(id);

        return "Employee Deleted";
    }

}
