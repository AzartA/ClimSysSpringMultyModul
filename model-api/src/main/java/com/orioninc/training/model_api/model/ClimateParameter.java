package com.orioninc.training.model_api.model;

import java.util.Set;

public interface ClimateParameter extends Entity {
    Set<? extends Entity> getUnits();

    Set<? extends Entity> getSensorTypes();
}
