package com.resourceradar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class EmployeeSkillKey implements Serializable {
    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "skill_id")
    private String skillId;

    @Column(name = "org_id")
    private String orgID;
}
