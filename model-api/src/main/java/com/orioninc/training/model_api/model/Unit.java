package com.orioninc.training.model_api.model;

import java.util.Set;

public interface Unit extends Entity {
    String getNotation();

    Set<? extends Entity> getClimateParameters();
}
