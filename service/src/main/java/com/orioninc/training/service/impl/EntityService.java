package com.orioninc.training.service.impl;

import com.orioninc.training.model.entities.Entity;
import com.orioninc.training.repo.api.RepositoryFasade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EntityService {
    private static final Logger LOG = LoggerFactory.getLogger(EntityService.class);
    private final RepositoryFasade repositoryFasade;

    @Autowired
    public EntityService(RepositoryFasade repositoryFasade) {
        this.repositoryFasade = repositoryFasade;
    }

    <E extends Entity> List<E> getAll(E entity){
        return repositoryFasade.get(entity).findAll();
    }

}
