package com.resourceradar.service.impl;

import com.resourceradar.repository.ProjectRepository;
import com.resourceradar.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

      @Autowired
     private ProjectRepository projectRepository;

}
