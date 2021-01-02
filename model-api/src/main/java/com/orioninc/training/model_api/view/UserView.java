package com.orioninc.training.model_api.view;


import com.orioninc.training.model_api.model.User;

import java.util.List;
import java.util.Optional;

public interface UserView extends View<User>, ViewType {
    Optional<? extends User> addSensors(long id, List<Long> sensorIds, User currentUser);

    Optional<? extends User> addRoles(long id, List<Long> rolesIds, User currentUser);

    Optional<? extends User> removeRoles(long id, List<Long> rolesIds, User currentUser);

    Optional<? extends User> getByLogin(String login);
}
