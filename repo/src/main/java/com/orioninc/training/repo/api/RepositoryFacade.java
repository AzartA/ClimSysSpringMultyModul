package com.orioninc.training.repo.api;

import com.orioninc.training.model.entities.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryFacade {
    JpaRepository<? extends Entity, Long> getRepository(String repoType);
    <R extends JpaRepository<? extends Entity, Long>> R get(Class<R> type);
    <R extends JpaRepository<E, Long>, E extends Entity> R  getByEntity(Class<E> entity);
}
