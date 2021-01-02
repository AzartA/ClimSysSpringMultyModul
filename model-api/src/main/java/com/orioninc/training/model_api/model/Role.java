package com.orioninc.training.model_api.model;

import java.util.Set;

public interface Role extends Entity {
    Set<? extends Entity> getUsers();
}
