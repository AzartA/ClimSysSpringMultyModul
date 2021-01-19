package com.orioninc.training.repo.api;

import com.orioninc.training.model.entities.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryFasade {
    <E extends Entity> JpaRepository<E, Long> get(E entityType);
}
