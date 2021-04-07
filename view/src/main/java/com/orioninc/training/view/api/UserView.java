package com.orioninc.training.view.api;

import com.orioninc.training.model.api.Filter;
import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.api.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserView extends View<User>, ViewType {
    Optional<? extends User> addSensors(long id, List<Long> sensorIds, User currentUser);

    Optional<? extends User> addRoles(long id, List<Long> rolesIds, User currentUser);

    Optional<? extends User> removeRoles(long id, List<Long> rolesIds, User currentUser);

    Optional<? extends User> getByLogin(String login);


    Optional<User> get(Long id);

    List<User> getAll();

    Set<Role> getRoles(long userId);

    long getCount(Filter filter, User currentUser);
}
