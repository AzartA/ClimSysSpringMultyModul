package com.orioninc.training.service.impl;

import com.orioninc.training.RepositoryFasade;
import com.orioninc.training.model.entities.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EntityService {
    private static final Logger LOG = LoggerFactory.getLogger(EntityService.class);
    private final RepositoryFasade repositoryFasade;

    @Autowired
    public EntityService(RepositoryFasade repositoryFasade) {
        this.repositoryFasade = repositoryFasade;
    }

    List<Entity> getAll(String repoName){
        return repositoryFasade.getRepository(repoName).findAll().stream().map(entity -> (Entity)entity).collect(Collectors.toList());
    }


}
