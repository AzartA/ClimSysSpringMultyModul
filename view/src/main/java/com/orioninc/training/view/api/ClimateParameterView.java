package com.orioninc.training.view.api;

import com.orioninc.training.model.api.entities.ClimateParameter;
import com.orioninc.training.model.api.entities.User;

import java.util.List;
import java.util.Optional;

public interface ClimateParameterView extends View<ClimateParameter>, ViewType {
    Optional<? extends ClimateParameter> addUnits(long id, List<Long> unitIds, User currentUser);
}
