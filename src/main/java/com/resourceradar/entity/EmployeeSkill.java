package com.resourceradar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_skill_mapping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSkill {
    @EmbeddedId
    private EmployeeSkillKey id = new EmployeeSkillKey();

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    @JsonBackReference
    private Skill skill;

    @ManyToOne
    @MapsId("orgID")
    @JoinColumn(name = "org_id")
    @JsonBackReference
    private Organization organization;

    @Column(name = "isPrimary")
    private Boolean isPrimary;

    @Column(name = "name")
    private String name;
}
