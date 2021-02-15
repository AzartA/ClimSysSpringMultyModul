package com.orioninc.training.service.api;

import java.util.List;

import com.orioninc.training.model.api.entities.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityService {
    <R extends JpaRepository<E, Long>, E extends Entity> List<E> getAllByRepo(Class<R> entityType);

    <E extends Entity> List<E> getAll(Class<E> entityType);
}
