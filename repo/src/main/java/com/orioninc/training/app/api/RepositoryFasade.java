package com.orioninc.training.app.api;

import com.orioninc.training.model.entities.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryFasade {
    <E extends Entity> JpaRepository<? extends Entity, Long> get(String repoType);
}
