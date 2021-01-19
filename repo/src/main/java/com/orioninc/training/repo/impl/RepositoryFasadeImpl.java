package com.orioninc.training.repo.impl;

import com.orioninc.training.model.entities.Entity;
import com.orioninc.training.repo.api.RepositoryFasade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RepositoryFasadeImpl implements RepositoryFasade {
    Map<? extends Entity, JpaRepository<? extends Entity,Long>> repositories;

    public void setRepositories(Map<Entity, JpaRepository<? extends Entity, Long>> repositories) {
        this.repositories = repositories;
    }

    public <E extends Entity> JpaRepository<E, Long> get(E entityType) {
        return (JpaRepository<E, Long>) repositories.get(entityType);

    }
}
