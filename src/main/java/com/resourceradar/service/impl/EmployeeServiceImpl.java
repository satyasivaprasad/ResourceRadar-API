package com.resourceradar.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public Employee createEmployee(EmployeeDto employeeDTO, HttpServletRequest request) {

        String orgId = request.getHeader("orgId");

        Employee employee = EmployeeMapper.INSTANCE.mapEmployee(employeeDTO);
        employee.setOrgId(orgId);

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

    public Employee getEmployee(String id) {
    	Employee emp= employeeRepository.findById(id).orElse(null);
    	return emp;
    }

    @Override
    public List<Employee> searchEmployee(String firstname, String lastname) {
    	System.out.println(firstname);
      List<Employee> Employees = employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(firstname,lastname);
      return Employees;
    }
    
	public void findemployee(String id) {
		Employee employee = employeeRepository.findById(id).orElse(null);
		System.out.println(employee.isActive());
		 employee.setActive(false);
		 System.out.println(employee.isActive());
		 employeeRepository.save(employee);

		
	}
	@Override
	public Optional<Employee> getEmployeeById(String id) {
		return Optional.empty();
	}
}
    

