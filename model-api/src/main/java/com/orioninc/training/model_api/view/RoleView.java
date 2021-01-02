package com.orioninc.training.model_api.view;

import com.orioninc.training.model_api.model.Role;
import com.orioninc.training.model_api.model.User;

import java.util.List;
import java.util.Optional;

public interface RoleView extends View<Role>, ViewType {
    Optional<? extends Role> addUsers(long id, List<Long> userIds, User currentUser);

    Optional<? extends Role> removeUsers(long id, List<Long> userIds, User currentUser);
}
