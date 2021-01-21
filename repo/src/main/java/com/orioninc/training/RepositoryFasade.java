package com.orioninc.training;

import com.orioninc.training.model.entities.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryFasade {
    JpaRepository<? extends Entity, Long> getRepository(String repoType);
    <R extends JpaRepository<? extends Entity, Long>> R get(Class<R> type);
}
