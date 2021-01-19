package com.orioninc.training.view.api;

import com.orioninc.training.model.entities.Entity;
import com.orioninc.training.model.entities.Role;
import com.orioninc.training.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserView extends View<User>, ViewType {
    Optional<? extends User> addSensors(long id, List<Long> sensorIds, User currentUser);

    Optional<? extends User> addRoles(long id, List<Long> rolesIds, User currentUser);

    Optional<? extends User> removeRoles(long id, List<Long> rolesIds, User currentUser);

    Optional<? extends User> getByLogin(String login);

    User getUser(Long id);

    List<User> getAll();

    List<Role> getRoles();
}
