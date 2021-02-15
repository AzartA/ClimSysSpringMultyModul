package com.orioninc.training.model.api.entities;

import java.util.Set;

public interface Role extends Entity {
    Set<? extends Entity> getUsers();
}
