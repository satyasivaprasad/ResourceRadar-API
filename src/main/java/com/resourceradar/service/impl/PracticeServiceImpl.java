package com.resourceradar.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.resourceradar.dto.PracticeDTO;
import com.resourceradar.entity.Practice;
import com.resourceradar.exception.PracticeNotFoundException;
import com.resourceradar.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourceradar.repository.PracticeRepository;

@Service
public class PracticeServiceImpl implements PracticeService {

	@Autowired
	private PracticeRepository practiceRepository;

	@Override
	public List<PracticeDTO> getAllPractices() throws PracticeNotFoundException {

		try {
			List<Practice> practices = practiceRepository.findAll();
			if(practices==null) {
				System.out.println("=========================================="+practices);
			}
			System.out.println("=========================================="+practices);
//			return practices.stream().map(this::convertToDto).collect(Collectors.toList());
			List<PracticeDTO> practiceDTOs= new ArrayList<>();
			PracticeDTO pDto= new PracticeDTO();
			for(Practice p: practices) {
			//	pDto=convertToDto(p);
				practiceDTOs.add(pDto);
			}
			return practiceDTOs;
		} catch (Exception e) {
			throw new PracticeNotFoundException("Practice Details not found"+e);
		}
	}

	@Override
	public List<PracticeDTO> getPracticeListBasedOnName(String name) {

//		List<Practice> practices = practiceRepository.findByNameContainingIgnoreCase(name);
//		return (PracticeDTO) practices.stream().map(this::convertToDto).collect(Collectors.toList());
		return null;

	}

	/*private PracticeDTO convertToDto(Practice practice) {
		PracticeDTO practiceDto = new PracticeDTO();
		//practiceDto.setId(practice.getId());
		practiceDto.setName(practice.getName());

//		LocalDateTime createdDateTime = practice.getCreatedDateTime().atZone(ZoneId.systemDefault()).toLocalDateTime();
//		LocalDateTime modifiedDateTime = practice.getModifiedDateTime().atZone(ZoneId.systemDefault())
//				.toLocalDateTime();

		practiceDto.setCreatedDateTime(practice.getCreatedDate());
		practiceDto.setModifiedDateTime(practice.getModifiedDate());

		return practiceDto;
	}

	private Practice convertToEntity(PracticeDTO practiceDto) {
		Practice practice = new Practice();
		practice.setId(practiceDto.getId());
		practice.setName(practiceDto.getName());

		Instant createdDateTime = practiceDto.getCreatedDateTime().atZone(ZoneId.systemDefault()).toInstant();
		Instant modifiedDateTime = practiceDto.getModifiedDateTime().atZone(ZoneId.systemDefault()).toInstant();

		practice.setCreatedDateTime(createdDateTime);
		practice.setModifiedDateTime(modifiedDateTime);

		return practice;
	}*/

	private Practice createEntityFromDto(PracticeDTO practiceDto) {
		Practice practice = new Practice();
		practice.setName(practiceDto.getName());
		practice.setCreatedDateTime(Instant.now());
		practice.setModifiedDateTime(Instant.now());

		return practice;
	}
}
