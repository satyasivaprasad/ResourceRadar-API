package com.resourceradar.controller;

import java.util.List;

import com.resourceradar.config.EndPointConfig;
import com.resourceradar.entity.Skills;
import com.resourceradar.service.impl.SkillsServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resourceradar.exception.SkillsNotFoundException;

@RestController
@RequestMapping(EndPointConfig.API_V1)
@Tag(name = "skills")
public class SkillsController {

	@Autowired
	private SkillsServiceImpl skillsService;

	@GetMapping(EndPointConfig.SKILLS)
	public ResponseEntity<List<Skills>> getAllSkills() throws SkillsNotFoundException {
		List<Skills> skills = skillsService.getAllSkills();

		if (skills.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(skills, HttpStatus.OK);
		}

	}
	 @GetMapping(EndPointConfig.SKILLS_DETAILS_SEARCH)
	    public ResponseEntity<List<Skills>> searchSkillsBasedOnName(@RequestParam("query") String query)  throws SkillsNotFoundException {
	        List<Skills> skills = skillsService.getSkillsListBasedOnName(query);

	        if (skills.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(skills, HttpStatus.OK);
	        }
	    }

}

