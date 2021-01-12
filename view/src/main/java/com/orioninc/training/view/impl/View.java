package com.orioninc.training.view.impl;

import com.orioninc.training.model.entities.Entity;
import com.orioninc.training.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface View<T extends Entity> {
    List<? extends T> getAll(
            List<FilterParam> filters, List<SortParam> sorts, int pg, int sz, User currentUser
    );

    long getCount(List<FilterParam> filters, int pg, int sz, User currentUser);

    Optional<? extends T> create(T entity, User currentUser);

    Optional<? extends T> update(long id, T entity, User currentUser);

    Optional<? extends T> get(long id, User currentUser);

    Optional<? extends T> delete(long id, User currentUser);
}
