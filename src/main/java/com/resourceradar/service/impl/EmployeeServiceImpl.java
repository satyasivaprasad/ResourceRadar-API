package com.resourceradar.service.impl;

import com.resourceradar.dto.EmployeeDto;
import com.resourceradar.entity.Employee;
import com.resourceradar.mapper.EmployeeMapper;
import com.resourceradar.repository.EmployeeRepository;
import com.resourceradar.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(@Autowired EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDTO, HttpServletRequest request) {

        String orgId = request.getHeader("orgId");

        Employee employee = EmployeeMapper.INSTANCE.mapEmployee(employeeDTO);
        employee.setOrgId(orgId);

//        Employee employee = new Employee();
//
//        employee.setNotes(employeeDTO.getNotes());
//        employee.setNickname(employeeDTO.getNickname());
//        employee.setStatus(employeeDTO.getStatus());
//        employee.setLocation(employeeDTO.getLocation());
//        employee.setOrgEmpId(employeeDTO.getOrgEmpId());
//        employee.setOrgId(orgId);
//        employee.setLastName(employeeDTO.getLastName());
//        employee.setFirstName(employeeDTO.getFirstName());
//        employee.setGender(employeeDTO.getGender());
//        employee.setContactNumber(employeeDTO.getContactNumber());
//        employee.setActive(employeeDTO.isActive());
//        employee.setBillable(employeeDTO.isBillable());
//        employee.setEmail(employeeDTO.getEmail());
//        employee.setDepartment(employeeDTO.getDepartments());
//        employee.setType(employeeDTO.getType());
//        employee.setDesignation(employeeDTO.getDesignation());
//        employee.setPractice(employeeDTO.getPractice());
//        employee.setCreatedBy(employeeDTO.getCreatedBy());
//        employee.setCreatedTime(employeeDTO.getCreatedTime());
//        employee.setModifiedBy(employeeDTO.getModifiedBy());
//        employee.setModifiedBy(employeeDTO.getModifiedBy());
//        employee.setFissionStartDate(employeeDTO.getFissionStartDate());


        Employee savedEmployee = employeeRepository.save(employee);

        log.info("employee save successfully =====>  " + savedEmployee.getId());
        return savedEmployee;


    }
}
