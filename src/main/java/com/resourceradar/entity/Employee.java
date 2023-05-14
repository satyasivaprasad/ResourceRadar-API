package com.resourceradar.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "select e from Employee e")
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "org_emp_id")
    private String orgEmpId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "type")
    private String type;

    @Column(name = "designation")
    private String designation;

    @Column(name = "billable")
    private boolean billable;

    @Column(name = "practice")
    private String practice;

    @Column(name = "exp_start_date")
    private LocalDateTime expStartDate;

    @Column(name = "fission_start_date")
    private LocalDateTime fissionStartDate;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "gender")
    private String gender;

    @Column(name = "location")
    private String location;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "active")
    private boolean active;

    @Column(name = "notes", length = 750)
    private String notes;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createdTime")
    private LocalDateTime createdTime;

    @Column(name = "modifiedBy")
    private String modifiedBy;

    @Column(name = "modifiedTime")
    private LocalDateTime modifiedTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy ="employee", cascade = CascadeType.ALL)
    public List<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<EmployeeSkill> employeeSkills) {
        this.skills = employeeSkills;
    }

    @ElementCollection
    private List<EmployeeSkill> skills = new LinkedList<EmployeeSkill>();
}
