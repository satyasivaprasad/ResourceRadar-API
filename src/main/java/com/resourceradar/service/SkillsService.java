package com.resourceradar.service;

import java.util.List;

import com.resourceradar.entity.Skills;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.resourceradar.exception.SkillsNotFoundException;

@Service
public interface SkillsService {

	public List<Skills> getAllSkills() throws SkillsNotFoundException;
	
	public List<Skills> getSkillsListBasedOnName(String name);


	Page<Skills> getAllSkills(Pageable pageable) throws SkillsNotFoundException;

	Page<Skills> getSkillsListBasedOnName(String name, Pageable pageable);
}
