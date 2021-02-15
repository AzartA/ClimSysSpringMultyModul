package com.orioninc.training.model.api.entities;

import java.util.Set;

public interface Unit extends Entity {
    String getNotation();

    Set<? extends Entity> getClimateParameters();
}
