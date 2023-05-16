package com.resourceradar.repository;

import com.resourceradar.entity.Designation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, String> {

	List<Designation> findByNameContainingIgnoreCase(String name);

	Page<Designation> findAll(Pageable pageable);

	Page<Designation> findByNameContainingIgnoreCase(String name, Pageable pageable);
}