package com.resourceradar.repository;

import com.resourceradar.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {


    Page<Project> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
