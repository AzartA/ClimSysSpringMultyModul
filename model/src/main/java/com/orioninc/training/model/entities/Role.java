package com.orioninc.training.model.entities;

import java.util.Set;

public interface Role extends Entity {
    Set<? extends Entity> getUsers();
}
