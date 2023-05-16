package com.resourceradar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Auditable {

    @JsonProperty("createdBy")
    @Column(name = "created_by")
    private String createdBy;

    @JsonProperty("createdTime")
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @JsonProperty("modifiedBy")
    @Column(name = "modified_by")
    private String modifiedBy;

    @JsonProperty("modifiedTime")
    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;

}
