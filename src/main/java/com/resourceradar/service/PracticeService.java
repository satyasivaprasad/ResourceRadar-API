package com.resourceradar.service;

import java.util.List;

import com.resourceradar.dto.PracticeDTO;
import org.springframework.stereotype.Service;

import com.resourceradar.exception.PracticeNotFoundException;

@Service
public interface PracticeService {

	public List<PracticeDTO> getAllPractices() throws PracticeNotFoundException;

	public List<PracticeDTO> getPracticeListBasedOnName(String name);
//	public Page<PracticeDTO> getAllPractices(Pageable pageable) throws PracticeNotFoundException;
//
//	public Page<PracticeDTO> getPracticeListBasedOnName(String name, Pageable pageable);

}