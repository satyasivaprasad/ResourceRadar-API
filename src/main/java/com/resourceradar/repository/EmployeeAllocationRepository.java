package com.resourceradar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resourceradar.entity.EmployeeAllocation;

@Repository
public interface EmployeeAllocationRepository extends JpaRepository<EmployeeAllocation, String> {

}
