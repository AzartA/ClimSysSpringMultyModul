package com.orioninc.training.app.impl;

import com.orioninc.training.model.entities.Entity;
import com.orioninc.training.app.api.RepositoryFasade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RepositoryFasadeImpl implements RepositoryFasade {
    @Autowired
    Map<String, JpaRepository<? extends Entity,Long>> repositories;
    Class<? extends  Entity> entityClass;

    public <E extends Entity> JpaRepository<? extends Entity, Long> get(String repoType) {
        return repositories.get(repoType);
    }


}
