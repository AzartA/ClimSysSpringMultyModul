package com.orioninc.training.view.impl;

import com.orioninc.training.model.entities.Role;
import com.orioninc.training.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface RoleView extends View<Role>, ViewType {
    Optional<? extends Role> addUsers(long id, List<Long> userIds, User currentUser);

    Optional<? extends Role> removeUsers(long id, List<Long> userIds, User currentUser);
}
