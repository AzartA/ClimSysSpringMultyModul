package com.orioninc.training.view.impl;

import com.orioninc.training.model.entities.Entity;
import com.orioninc.training.model.entities.Role;
import com.orioninc.training.model.entities.User;
import com.orioninc.training.view.api.FilterParam;
import com.orioninc.training.view.api.RoleView;
import com.orioninc.training.view.api.SortParam;
import com.orioninc.training.view.api.ViewType;

import java.util.List;
import java.util.Optional;

public class RoleViewImpl implements RoleView {


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
    public List<? extends Role> getAll(List<FilterParam> filters, List<SortParam> sorts, int pg, int sz, User currentUser) {
        return null;
    }

    @Override
    public long getCount(List<FilterParam> filters, int pg, int sz, User currentUser) {
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
