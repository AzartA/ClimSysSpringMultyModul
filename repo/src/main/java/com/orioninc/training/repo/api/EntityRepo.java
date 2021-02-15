package com.orioninc.training.repo.api;

import com.orioninc.training.model.api.entities.Entity;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface EntityRepo<E extends Entity> extends JpaRepository<E,Long>, JpaSpecificationExecutor<E> {
}
