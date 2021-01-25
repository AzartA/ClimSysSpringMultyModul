package com.orioninc.training.repo.impl;

import com.orioninc.training.repo.api.RepositoryFasade;
import com.orioninc.training.model.entities.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor(onConstructor =  @__(@Autowired))
@Service
public class RepositoryFasadeImpl implements RepositoryFasade {
    private final Map<String, JpaRepository<? extends Entity, Long>> repositories;

    public JpaRepository<? extends Entity, Long> getRepository(String repoType) {
        return repositories.get(repoType);
    }

    @SuppressWarnings (value="unchecked")
    public <R extends JpaRepository<? extends Entity, Long>> R  get(Class<R> type) {
        return (R) repositories.get(getBeanName(type));
    }

    private <R> String getBeanName(Class<R>type){
        char[] typeName = type.getSimpleName().toCharArray();
        typeName[0] += 32;
        return new String(typeName);
    }


}
