package com.orioninc.training.service.impl;

import com.orioninc.training.model.api.Filter;
import com.orioninc.training.model.api.SortParam;
import com.orioninc.training.model.api.entities.Entity;
import com.orioninc.training.model.filters.FilterImpl;
import com.orioninc.training.repo.api.EntityRepo;
import com.orioninc.training.repo.api.GenericSpecification;
import com.orioninc.training.repo.api.RepositoryFacade;
import com.orioninc.training.repo.impl.GenericSpecificationImpl;
import com.orioninc.training.service.api.EntityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class EntityServiceImpl implements EntityService {
    private static final Logger LOG = LoggerFactory.getLogger(EntityServiceImpl.class);
    private final RepositoryFacade repositoryFacade;

    @Override
    public <R extends EntityRepo<E,Y>, E extends Entity, Y extends E> List<E> getAllByRepo(Class<R> repoType) {
        return new ArrayList<>(repositoryFacade.get(repoType).findAll());
    }

    @Override
    public <E extends Entity> List<E> getAll(Class<E> entityType) {
        return new ArrayList<>(repositoryFacade.getByEntity(entityType).findAll());
    }

    @Override
    public <E extends Entity> Page<E> getAllWithFilter(Filter filter, List<SortParam> sorts, int pg, int sz, Class<E> entityType) {
        if(filter == null && sorts.isEmpty() && pg== 0){
            return new PageImpl<>(getAll(entityType));
        }
        GenericSpecification<E> spec =
                new GenericSpecificationImpl<>(new FilterImpl(filter));
        if(pg==0){
            if(sorts.isEmpty()){
                List<E> list =repositoryFacade.getByEntity(entityType).findAll(spec);
                return new PageImpl<>(list);
            }
            Sort sort = getSort(sorts);
            List<E> list =repositoryFacade.getByEntity(entityType).findAll(spec,sort);
            return new PageImpl<>(list);
        }
        Pageable pageable = getPageable(sorts, pg, sz);
        return repositoryFacade.getByEntity(entityType).findAll(spec, pageable);
    }

    private Sort getSort(List<SortParam> sorts) {
        List<Sort.Order> orders = new ArrayList<>();
        for(SortParam s : sorts){
            orders.add(new Sort.Order(Sort.Direction.valueOf(s.getOrder()),s.getBy()));
        }
        return Sort.by(orders);
    }

    private Pageable getPageable(List<SortParam> sorts, int pg, int sz) {
        Pageable pageable;
        if(!sorts.isEmpty()) {
            Sort sort = getSort(sorts);
            pageable = PageRequest.of(pg-1,sz,sort);
        }else{
            pageable = PageRequest.of(pg-1,sz);
        }
        return pageable;
    }

    @Override
    public <E extends Entity> long countWithFilter(Filter filter, Class<E> entityType) {
        GenericSpecification<E> spec =
                new GenericSpecificationImpl<>(new FilterImpl(filter));
        return repositoryFacade.getByEntity(entityType).count(spec);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public <Y extends Entity> Y save(Y entityDo) {
        Class<Y> entityDoType = (Class<Y>) entityDo.getClass();
        return repositoryFacade.getByDo(entityDoType).save(entityDo);
    }

    @Override
    public <Y extends Entity> void deleteDo(long id, Class<Y> entityDoType) {
        repositoryFacade.getByDo(entityDoType).deleteById(id);
    }

    @Override
    public <E extends Entity> void deleteEntity(long id, Class<E> entityType) {
        repositoryFacade.getByEntity(entityType).deleteById(id);
    }

    @Override
    public <Y extends Entity> Optional<Y> getDoById(long id, Class<Y> entityDoType) {
        return repositoryFacade.getByDo(entityDoType).findById(id);
    }

    public <E extends Entity> Optional<E> getEntityById(long id, Class<E> entityType) {
        return repositoryFacade.getByEntity(entityType).findById(id);
    }

    @Override
    public <E extends Entity> List<E> getEntityByName(String name, Class<E> entityType) {
        return repositoryFacade.getByEntity(entityType).getByName(name);
    }

    @Override
    public <Y extends Entity> List<Y> getDoByName(String name, Class<Y> entityDoType) {
        return repositoryFacade.getByDo(entityDoType).getByName(name);
    }
}
