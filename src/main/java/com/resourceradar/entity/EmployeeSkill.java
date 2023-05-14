package com.resourceradar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "employees_skills")
public class EmployeeSkill implements Serializable {
    @Column(name = "is_primary")
    private boolean isPrimary;

    private EmployeeSkillId pk = new EmployeeSkillId();

    @EmbeddedId
    private EmployeeSkillId getPk() {
        return pk;
    }

    private void setPk(EmployeeSkillId pk) {
        this.pk = pk;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @Transient
    public Employee getEmployee() {
        return getPk().getEmployee();
    }

    public void setEmployee(Employee employee) {
        getPk().setEmployee(employee);
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    @Transient
    public Skill getSkill() {
        return getPk().getSkill();
    }

    public void setSkill(Skill skill) {
        getPk().setSkill(skill);
    }
}
