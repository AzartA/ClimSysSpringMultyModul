package com.orioninc.training.model_api.model;

import java.util.Set;

public interface Sensor extends Entity {

    Entity getLocation();

    long getX();

    long getY();

    Entity getType();

    Set<? extends Entity> getUsers();
}
