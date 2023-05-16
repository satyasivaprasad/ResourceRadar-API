package com.resourceradar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class EmployeeSkillKey implements Serializable {
    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "skill_id")
    private String skillId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeSkillKey that = (EmployeeSkillKey) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, skillId);
    }
}
