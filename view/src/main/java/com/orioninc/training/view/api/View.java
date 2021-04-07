package com.orioninc.training.view.api;



import com.orioninc.training.model.api.Filter;
import com.orioninc.training.model.api.SortParam;
import com.orioninc.training.model.api.entities.Entity;
import com.orioninc.training.model.api.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface View<T extends Entity> {
    Page<T> getAll(Filter filter, List<SortParam> sorts, int pg, int sz, User currentUser);

    long getCount(Filter filter, User currentUser);

    Optional<? extends T> create(T entity, User currentUser);

    Optional<? extends T> update(long id, T entity, User currentUser);

    Optional<? extends T> get(long id, User currentUser);

    Optional<? extends T> delete(long id, User currentUser);

    List<User> findByName(String name);
}
