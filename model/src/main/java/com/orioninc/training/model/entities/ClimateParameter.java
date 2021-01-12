package com.orioninc.training.model.entities;

import java.util.Set;

public interface ClimateParameter extends Entity {
    Set<? extends Entity> getUnits();

    Set<? extends Entity> getSensorTypes();
}
