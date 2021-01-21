package com.orioninc.training.impl;

import com.orioninc.training.RepositoryFasade;
import com.orioninc.training.model.entities.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RepositoryFasadeImpl implements RepositoryFasade {
    @Autowired
    Map<String, JpaRepository<? extends Entity, Long>> repositories;
    Class<? extends  Entity> entityClass;

    public JpaRepository<? extends Entity, Long> getRepository(String repoType) {
        return repositories.get(repoType);
    }

    @SuppressWarnings (value="unchecked")
    public <R extends JpaRepository<? extends Entity, Long>> R  get(Class<R> type) {
        return (R) repositories.get(type.getSimpleName());
    }


}
