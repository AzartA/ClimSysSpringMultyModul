package com.orioninc.training.repo.api;

import com.orioninc.training.model.api.entities.Entity;
import com.orioninc.training.model.dos.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RepositoryFacade {
    JpaRepository<? extends Entity, Long> getRepository(String repoType);
    <R extends JpaRepository<? extends Entity, Long>> R get(Class<R> type);
    <R extends EntityRepo<E,Y>, E extends Entity, Y extends E> R  getByEntity(Class<E> entity);
    <R extends EntityRepo<E,Y>, E extends Entity, Y extends E> R  getByDo(Class<Y> entityDo);
}
