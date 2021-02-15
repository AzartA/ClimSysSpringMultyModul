package com.orioninc.training.repo.impl;

import com.orioninc.training.model.dos.RoleDO;
import com.orioninc.training.repo.api.RepositoryFacade;
import com.orioninc.training.model.api.entities.Entity;
import com.orioninc.training.repo.api.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor =  @__(@Autowired))
@Service
public class RepositoryFacadeImpl implements RepositoryFacade {
    private final Map<String, JpaRepository<? extends Entity, Long>> repositories;


    public JpaRepository<? extends Entity, Long> getRepository(String repoType) {
        return repositories.get(repoType);
    }

    @SuppressWarnings (value="unchecked")
    public <R extends JpaRepository<? extends Entity, Long>> R  get(Class<R> type) {
        return (R) repositories.get(getBeanName(type));
    }

    @SuppressWarnings (value="unchecked")
    public <R extends JpaRepository<E, Long>, E extends Entity> R  getByEntity(Class<E> entity) {
        String entityBeanName = getBeanName(entity);
        String repoBeanName = entityBeanName+"Repo";
        return (R) repositories.get(repoBeanName);
    }

    private <R> String getBeanName(Class<R>type){
        char[] typeName = type.getSimpleName().toCharArray();
        typeName[0] += 32;
        return new String(typeName);
    }



}
