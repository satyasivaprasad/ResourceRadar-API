package com.resourceradar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "manager_tbl")
@Data

public class Manager {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Column(name = "type")
    private  String type;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
    private Employee employee;
    
    @OneToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private  Client client;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
    private Project project;

    @Column(name = "org_id")
    private String orgId;
    
    public Manager() {
    }
    public Manager(String id) {
        this.id = id;
    }

    @Column(name = "modified_by")
    private String modifiedBy;

 	@Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    
    @Column(name = "name")
    private String name;
}
    

    // getters and setters

