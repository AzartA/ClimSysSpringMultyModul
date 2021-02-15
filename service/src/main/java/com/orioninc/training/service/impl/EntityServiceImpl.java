package com.orioninc.training.service.impl;

import com.orioninc.training.model.api.entities.Entity;
import com.orioninc.training.repo.api.RepositoryFacade;
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

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class EntityServiceImpl implements EntityService {
    private static final Logger LOG = LoggerFactory.getLogger(EntityServiceImpl.class);
    private final RepositoryFacade repositoryFacade;

    public <R extends JpaRepository<E, Long>, E extends Entity> List<E> getAllByRepo(Class<R> repoType) {
        return new ArrayList<>(repositoryFacade.get(repoType).findAll());
    }

    public <E extends Entity> List<E> getAll(Class<E> entityType) {
        return new ArrayList<>(repositoryFacade.getByEntity(entityType).findAll());
    }
}
