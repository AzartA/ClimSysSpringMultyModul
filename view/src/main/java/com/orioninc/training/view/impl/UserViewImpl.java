package com.orioninc.training.view.impl;

import com.orioninc.training.model.api.Filter;
import com.orioninc.training.model.api.entities.Entity;
import com.orioninc.training.service.api.EntityService;
import com.orioninc.training.model.api.SortParam;
import com.orioninc.training.view.api.UserView;
import com.orioninc.training.view.api.ViewType;
import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.api.entities.User;
import com.orioninc.training.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor(onConstructor =  @__(@Autowired))
@Component
public class UserViewImpl implements UserView {
    private final EntityService entityService;
    private final UserService userService;

    @Override
    public Optional<? extends User> addSensors(long id, List<Long> sensorIds, User currentUser) {
        return Optional.empty();
    }

    @Override
    public Optional<? extends User> addRoles(long id, List<Long> rolesIds, User currentUser) {
        return Optional.empty();
    }

    @Override
    public Optional<? extends User> removeRoles(long id, List<Long> rolesIds, User currentUser) {
        return Optional.empty();
    }

    @Override
    public Optional<? extends User> getByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> get(Long id) {
        return entityService.getEntityById(id,User.class);
    }

    @Override
    public List<User> getAll() {
        return entityService.getAll(User.class);
    }

    @Override
    public Set<Role> getRoles(long userId) {
        return userService.getRoles(userId);
    }

    @Override
    public Page<User> getAll(Filter filter, List<SortParam> sorts, int pg, int sz, User currentUser) {
        return entityService.getAllWithFilter(filter, sorts, pg, sz, User.class);
    }

    @Override
    public long getCount(Filter filter, User currentUser) {
        return entityService.countWithFilter(filter,User.class);
    }

    @Override
    public Optional<? extends User> create(User entity, User currentUser) {
        return Optional.empty();
    }

    @Override
    public Optional<? extends User> update(long id, User entity, User currentUser) {
        return Optional.empty();
    }

    @Override
    public Optional<? extends User> get(long id, User currentUser) {
        return Optional.empty();
    }

    @Override
    public Optional<? extends User> delete(long id, User currentUser) {
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
