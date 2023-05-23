package com.resourceradar.dto;

import com.resourceradar.entity.Department;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class EmployeeDto {

    private String orgEmpId;
    private String firstName;
    private String lastName;
    private String email;
    private String type;
    private String designation;
    private boolean isBillable;
    private String practice;
    private LocalDateTime expStartDate;
    private LocalDateTime fissionStartDate;
    private String contactNumber;
    private String status;

    private Department department;
    private String gender;
    private String location;
    private String nickname;
    private boolean isActive;
    private String notes;
    private String createdBy;
    private LocalDateTime createdTime;
    private String modifiedBy;
    private LocalDateTime modifiedTime;

    private Set<EmployeeSkillsDto> skills = new HashSet<>();
    private Set<EmployeeOrgRolesDto> roles = new HashSet<>();
}
