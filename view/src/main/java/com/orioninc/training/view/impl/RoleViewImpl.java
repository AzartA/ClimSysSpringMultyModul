package com.orioninc.training.view.impl;

import com.orioninc.training.model.api.Filter;
import com.orioninc.training.model.api.entities.Entity;
import com.orioninc.training.service.api.EntityService;
import com.orioninc.training.view.api.RoleView;
import com.orioninc.training.model.api.SortParam;
import com.orioninc.training.view.api.ViewType;
import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.api.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor =  @__(@Autowired))
public class RoleViewImpl implements RoleView {

    private final EntityService entityService;

    @Override
    public Optional<? extends Role> addUsers(long id, List<Long> userIds, User currentUser) {
        return Optional.empty();
    }

    @Override
    public Optional<? extends Role> removeUsers(long id, List<Long> userIds, User currentUser) {
        return Optional.empty();
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public Page<Role> getAll(Filter filter, List<SortParam> sorts, int pg, int sz, User currentUser) {
        return null;
    }

    @Override
    public long getCount(Filter filter, User currentUser) {
        return 0;
    }

    @Override
    public Optional<? extends Role> create(Role entity, User currentUser) {
        return Optional.empty();
    }

    @Override
    public Optional<? extends Role> update(long id, Role entity, User currentUser) {
        return Optional.empty();
    }

    @Override
    public Optional<? extends Role> get(long id, User currentUser) {
        return Optional.empty();
    }

    @Override
    public Optional<? extends Role> delete(long id, User currentUser) {
        return Optional.empty();
    }

    @Override
    public List<User> findByName(String name) {
       return entityService.getEntityByName(name,User.class);
    }

    @Override
    public Class<? extends Entity> getType() {
        return null;
    }

    @Override
    public Class<? extends ViewType> getServiceClass() {
        return null;
    }

    @Override
    public ViewType get() {
        return null;
    }
}
