package com.resourceradar.repository;

import java.util.List;

import com.resourceradar.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, String> {

	List<Skills> findByNameContainingIgnoreCase(String name);
}
