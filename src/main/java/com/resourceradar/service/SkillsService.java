package com.resourceradar.service;

import java.util.List;

import com.resourceradar.entity.Skill;
import org.springframework.stereotype.Service;

import com.resourceradar.exception.SkillsNotFoundException;

@Service
public interface SkillsService {

	public List<Skill> getAllSkills() throws SkillsNotFoundException;
	
	public List<Skill> getSkillsListBasedOnName(String name);
	

}
