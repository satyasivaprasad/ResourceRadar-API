package com.resourceradar.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.resourceradar.entity.Organization;
import com.resourceradar.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.EmployeeDto;
import com.resourceradar.dto.EmployeeSkillsDto;
import com.resourceradar.entity.Employee;
import com.resourceradar.entity.EmployeeSkill;
import com.resourceradar.entity.Skill;
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
    private OrganizationRepository organizationRepository;

    @Override
    public Employee createEmployee(EmployeeDto employeeDTO, HttpServletRequest request) {

        String orgId = request.getHeader("orgId");

        Employee employee = EmployeeMapper.INSTANCE.mapEmployee(employeeDTO);
        employee.setOrgId(orgId);

        Optional<Organization> organization = organizationRepository.findById(orgId);

        if (!employeeDTO.getSkills().isEmpty()) {
            Set<EmployeeSkill> employeeSkillsList = new HashSet<>();
            for (EmployeeSkillsDto employeeSkillDto :
                    employeeDTO.getSkills()) {
                Optional<Skill> s = skillsRepository.findById(employeeSkillDto.getId());
                EmployeeSkill employeeSkill = new EmployeeSkill();
                employeeSkill.setSkill(s.get());
                employeeSkill.setEmployee(employee);
                employeeSkill.setOrganization(organization.get());
                employeeSkill.setIsPrimary(employeeSkillDto.getIsPrimary());
                employeeSkill.setName(employeeSkillDto.getName());
                employeeSkillsList.add(employeeSkill);
            }
            employee.setSkills(employeeSkillsList);
        }

        Employee savedEmployee = employeeRepository.save(employee);

        log.info("employee save successfully =====>  " + savedEmployee.getId());
        return savedEmployee;
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employeeRepository.findByOrgEmpId(id);
    }

    @Override
    public List<Employee> searchEmployee(String query) {
      List<Employee> Employees = employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query);
      return Employees;
    }
    
	public void findemployee(String id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		 employee.get().setActive(false);

		
	}
}
    

