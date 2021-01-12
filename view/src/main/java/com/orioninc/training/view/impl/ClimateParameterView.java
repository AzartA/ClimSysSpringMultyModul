package com.orioninc.training.view.impl;

import com.orioninc.training.model.entities.ClimateParameter;
import com.orioninc.training.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface ClimateParameterView extends View<ClimateParameter>, ViewType {
    Optional<? extends ClimateParameter> addUnits(long id, List<Long> unitIds, User currentUser);
}
