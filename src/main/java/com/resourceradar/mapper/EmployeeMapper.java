package com.resourceradar.mapper;

import com.resourceradar.dto.EmployeeDto;
import com.resourceradar.dto.EmployeeSkillsDto;
import com.resourceradar.entity.Employee;
import com.resourceradar.entity.EmployeeSkill;
import com.resourceradar.entity.EmployeeSkillKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee mapEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> mapToEmployeeListDto(List<Employee> employees);

    EmployeeDto mapToEmployeeDto(Employee employee);

    EmployeeSkillKey map(String value);

    @Mapping(source = "employeeSkills.skillId", target = "id")
    EmployeeSkillsDto mapToDto(EmployeeSkill employeeSkill);
    List<EmployeeSkillsDto> toEmployeeListDto(List<EmployeeSkill> employees);
}