package com.orioninc.training.service.api;

import com.orioninc.training.model.entities.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntityService {
    <R extends JpaRepository<E, Long>, E extends Entity> List<E> getAllByRepo(Class<R> entityType);
    <E extends Entity> List<E> getAll(Class<E> entityType);
}
