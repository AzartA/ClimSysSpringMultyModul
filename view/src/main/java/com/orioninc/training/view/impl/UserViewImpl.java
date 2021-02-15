package com.orioninc.training.view.impl;

import com.orioninc.training.model.api.entities.Entity;
import com.orioninc.training.view.api.FilterParam;
import com.orioninc.training.view.api.SortParam;
import com.orioninc.training.view.api.UserView;
import com.orioninc.training.view.api.ViewType;
import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.api.entities.User;
import com.orioninc.training.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor =  @__(@Autowired))
@Component
public class UserViewImpl implements UserView {
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
    public User getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }

    @Override
    public List<Role> getRoles() {
        return userService.getRoles();
    }

    @Override
    public List<? extends User> getAll(List<FilterParam> filters, List<SortParam> sorts, int pg, int sz, User currentUser) {
        return null;
    }

    @Override
    public long getCount(List<FilterParam> filters, int pg, int sz, User currentUser) {
        return 0;
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
