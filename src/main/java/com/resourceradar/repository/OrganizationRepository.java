package com.resourceradar.repository;

import com.resourceradar.entity.Organization;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {

	
	          Optional<Organization> findById(String id);

}