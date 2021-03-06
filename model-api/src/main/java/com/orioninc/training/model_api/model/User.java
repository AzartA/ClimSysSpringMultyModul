package com.orioninc.training.model_api.model;

import java.util.Set;

public interface User extends Entity {
    String getLogin();

    String getPassword();

    Set<String> getProperties();

    Set<? extends Entity> getSensors();

    Set<? extends Entity> getRoles();
}
