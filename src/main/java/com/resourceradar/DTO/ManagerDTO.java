package com.resourceradar.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ManagerDTO {

   private String id;

    private String name;

    private  String type;
    private String createdBy;

    private String modifiedBy;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}
