package com.orioninc.training.service.api;

import java.util.List;
import java.util.Optional;

import com.orioninc.training.model.api.Filter;
import com.orioninc.training.model.api.SortParam;
import com.orioninc.training.model.api.entities.Entity;
import com.orioninc.training.repo.api.EntityRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntityService {
    <R extends EntityRepo<E, Y>, E extends Entity, Y extends E> List<E> getAllByRepo(Class<R> repoType);

    <E extends Entity> List<E> getAll(Class<E> entityType);

    <E extends Entity> Page<E> getAllWithFilter(Filter filter, List<SortParam> sorts, int pg, int sz, Class<E> entityType);

    <E extends Entity> long countWithFilter(Filter filter, Class<E> entityType);

    <Y extends Entity> Y save(Y entityDo);

    <Y extends Entity> void deleteDo(long id, Class<Y> entityDoType);

    <E extends Entity> void deleteEntity(long id, Class<E> entityType);

    <Y extends Entity> Optional<Y> getDoById(long id, Class<Y> entityDoType);

    <E extends Entity> Optional<E> getEntityById(long id, Class<E> entityType);

    <E extends Entity> List<E> getEntityByName(String name, Class<E> entityType);

    <Y extends Entity> List<Y> getDoByName(String name, Class<Y> entityType);
}
