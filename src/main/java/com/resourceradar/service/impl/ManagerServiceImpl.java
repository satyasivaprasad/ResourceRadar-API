package com.resourceradar.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourceradar.dto.ManagerDto;
import com.resourceradar.entity.Manager;
import com.resourceradar.repository.ManagerRepository;
import com.resourceradar.service.ManagerService;
@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<ManagerDto> getAllManagers() {
		List<Manager> all = managerRepository.findAll();
		List<ManagerDto> list = all.stream().map(p->mapper.map(p, ManagerDto.class)).collect(Collectors.toList());
		return list;
	}

}
