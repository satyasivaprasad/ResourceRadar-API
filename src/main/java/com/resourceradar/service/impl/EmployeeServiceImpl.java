package com.resourceradar.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SkillsRepository skillsRepository;

    public EmployeeServiceImpl(@Autowired EmployeeRepository employeeRepository, @Autowired SkillsRepository skillsRepository) {
        this.employeeRepository = employeeRepository;
        this.skillsRepository = skillsRepository;
    }

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
                employeeSkillsList.add(employeeSkill);
            }
            employee.setSkills(employeeSkillsList);
        }

        Employee savedEmployee = employeeRepository.save(employee);

        log.info("employee save successfully =====>  " + savedEmployee.getId());
        return savedEmployee;
    }
}
