package com.resourceradar.service.impl;

import com.resourceradar.dto.PracticeDTO;
import com.resourceradar.entity.Practice;
import com.resourceradar.exception.PracticeNotFoundException;
import com.resourceradar.repository.DesignationRepository;
import com.resourceradar.repository.ProjectRepository;
import com.resourceradar.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

}