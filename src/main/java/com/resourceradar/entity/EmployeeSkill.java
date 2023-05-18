package com.resourceradar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_skill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeeSkill {
    @EmbeddedId
    private EmployeeSkillKey id = new EmployeeSkillKey();

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Column(name = "isPrimary")
    private Boolean isPrimary;

}
