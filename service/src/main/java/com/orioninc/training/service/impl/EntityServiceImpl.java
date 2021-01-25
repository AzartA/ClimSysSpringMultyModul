package com.orioninc.training.service.impl;

import com.orioninc.training.repo.api.RepositoryFasade;
import com.orioninc.training.model.entities.Entity;
import com.orioninc.training.service.api.EntityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor =  @__(@Autowired))
@Service
@Transactional
public class EntityServiceImpl implements EntityService {
    private static final Logger LOG = LoggerFactory.getLogger(EntityServiceImpl.class);
    private final RepositoryFasade repositoryFasade;

    public <R extends JpaRepository<E, Long>, E extends Entity> List<E> getAll(Class<R> entityType){
        return new ArrayList<>(repositoryFasade.get(entityType).findAll());
    }


}