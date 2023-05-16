package com.resourceradar.repository;

import java.util.List;

import com.resourceradar.entity.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, String> {

	List<Practice> findByNameContainingIgnoreCase(String name);

}
