package com.resourceradar.repository;

import com.resourceradar.entity.ApplicationRole;
import com.resourceradar.entity.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ApplicationRoleRepository extends JpaRepository<ApplicationRole, String>{

   List<ApplicationRole> findByNameContainingIgnoreCase(String name);

}
