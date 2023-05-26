package com.resourceradar.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.resourceradar.dto.EmployeeOrgRolesDto;
import com.resourceradar.entity.*;
import com.resourceradar.repository.ApplicationRoleRepository;
import com.resourceradar.repository.OrganizationRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.EmployeeDto;
import com.resourceradar.dto.EmployeeSkillsDto;
import com.resourceradar.mapper.EmployeeMapper;
import com.resourceradar.repository.EmployeeRepository;
import com.resourceradar.repository.SkillsRepository;
import com.resourceradar.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private ApplicationRoleRepository applicationRoleRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ModelMapper mapper;
    
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDTO, HttpServletRequest request) {

        String orgId = request.getHeader("orgId");

        Optional<Organization> organization = organizationRepository.findById(orgId);

        Employee employee = EmployeeMapper.INSTANCE.mapEmployee(employeeDTO);
        employee.setOrgId(orgId);

        // TODO: Refactor using mapper
        if (!employeeDTO.getSkills().isEmpty()) {
            Set<EmployeeSkill> employeeSkillsList = new HashSet<>();
            for (EmployeeSkillsDto employeeSkillDto :
                    employeeDTO.getSkills()) {
                Optional<Skill> s = skillsRepository.findById(employeeSkillDto.getId());
                EmployeeSkill employeeSkill = new EmployeeSkill();
                employeeSkill.setSkill(s.get());
                employeeSkill.setEmployee(employee);
                employeeSkill.setIsPrimary(employeeSkillDto.getIsPrimary());
                employeeSkill.setName(employeeSkillDto.getName());
                employeeSkill.setOrganization(organization.get());
                employeeSkillsList.add(employeeSkill);
            }
            employee.setSkills(employeeSkillsList);
        }

        if (!employeeDTO.getRoles().isEmpty()) {
            Set<EmployeeOrgRole> employeeOrgRoles = new HashSet<>();
            for (EmployeeOrgRolesDto employeeOrgRoleDto :
                    employeeDTO.getRoles()) {
                Optional<ApplicationRole> s = applicationRoleRepository.findById(employeeOrgRoleDto.getId());
                EmployeeOrgRole employeeOrgRole = new EmployeeOrgRole();
                employeeOrgRole.setApplicationRole(s.get());
                employeeOrgRole.setEmployee(employee);
                employeeOrgRole.setRole(employeeOrgRoleDto.getRole());
                employeeOrgRole.setOrganization(organization.get());
                employeeOrgRoles.add(employeeOrgRole);
            }
            employee.setRoles(employeeOrgRoles);
        }

        Employee savedEmployee = employeeRepository.save(employee);

        log.info("employee save successfully =====>  " + savedEmployee.getId());
        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.mapToEmployeeDto(employee);
        Set<EmployeeSkillsDto> employeeSkillsDtos = employee.getSkills().stream().map((EmployeeMapper.INSTANCE::mapToDto)).collect(Collectors.toSet());
        employeeDto.setSkills(employeeSkillsDtos);
        Set<EmployeeOrgRolesDto> employeeOrgRolesDtos = employee.getRoles().stream().map((EmployeeMapper.INSTANCE::mapToEmployeeOrgRolesDto)).collect(Collectors.toSet());
        employeeDto.setRoles(employeeOrgRolesDtos);

        return employeeDto;
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(String id) {
        Employee emp= employeeRepository.findById(id).orElse(null);
        return emp;
    }

    @Override
    public List<Employee> searchEmployee(String firstname, String lastname) {
      List<Employee> Employees = employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(firstname,lastname);
      return Employees;
    }

	public void deleteEmployee(String id) {
		Employee employee = employeeRepository.findById(id).orElse(null);
		 employee.setActive(false);
		 employeeRepository.save(employee);
    }
    @Override
    public Optional<Employee> getEmployeeById(String id) {
        return Optional.empty();
    }

    @Override
    public Employee getEmployeeByEmailId(String emailId) {
        return employeeRepository.findByEmail(emailId);
    }
}

