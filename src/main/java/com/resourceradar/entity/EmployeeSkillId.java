package com.resourceradar.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeSkillId implements Serializable {
    private Employee employee;

    private Skill skill;

    @ManyToOne
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @ManyToOne
    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeSkillId that = (EmployeeSkillId) o;
        return employee.equals(that.employee) && skill.equals(that.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, skill);
    }
}
