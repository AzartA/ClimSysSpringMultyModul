package com.orioninc.training.model.api.entities;

import java.util.Set;

public interface ClimateParameter extends Entity {
    Set<? extends Entity> getUnits();

    Set<? extends Entity> getSensorTypes();
}
