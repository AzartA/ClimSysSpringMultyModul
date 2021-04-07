package com.orioninc.training.repo.api;

import com.orioninc.training.model.api.entities.Entity;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface EntityRepo<E extends Entity, Y extends E> extends JpaRepository<Y,Long>, JpaSpecificationExecutor<E> {
    List<E> getByName(String name);
}
