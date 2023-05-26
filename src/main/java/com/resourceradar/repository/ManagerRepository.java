package com.resourceradar.repository;

import com.resourceradar.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {
	
	@Query(value = "select * from manager_tbl where client_id = ?1", nativeQuery = true)
	Manager findbyClientId(String client_id);
}
