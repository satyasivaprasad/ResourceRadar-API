package com.resourceradar.service.impl;

import java.util.List;

import com.resourceradar.entity.Skills;
import com.resourceradar.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.resourceradar.exception.SkillsNotFoundException;
import com.resourceradar.repository.SkillsRepository;

@Service
public class SkillsServiceImpl implements SkillsService {

	@Autowired
	private SkillsRepository skillsRepository;

	@Override
	public List<Skills> getAllSkills() throws SkillsNotFoundException {

		try {
			List<Skills> skills = skillsRepository.findAll();

			return skills;
		} catch (Exception e) {
			throw new SkillsNotFoundException(" Skills Details not found");
		}
	}

	@Override
	public List<Skills> getSkillsListBasedOnName(String name) {

		List<Skills> skills = skillsRepository.findByNameContainingIgnoreCase(name);
		return skills;

	}

	@Override
	public Page<Skills> getAllSkills(Pageable pageable) throws SkillsNotFoundException {
		return skillsRepository.findAll(pageable);
	}


	@Override
	public Page<Skills> getSkillsListBasedOnName(String name, Pageable pageable) {
		return skillsRepository.findByNameContainingIgnoreCase(name, pageable);
	}

}
