package com.orioninc.training.model.api.entities;

import javax.persistence.MappedSuperclass;
import java.util.Set;

@MappedSuperclass
public interface User extends Entity {
    String getLogin();

    String getPassword();

    Set<String> getProperties();

    Set<? extends Entity> getSensors();

    Set<? extends Entity> getRoles();
}
