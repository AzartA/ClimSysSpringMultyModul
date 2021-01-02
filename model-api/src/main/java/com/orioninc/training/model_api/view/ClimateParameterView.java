package com.orioninc.training.model_api.view;

import com.orioninc.training.model_api.model.ClimateParameter;
import com.orioninc.training.model_api.model.User;

import java.util.List;
import java.util.Optional;

public interface ClimateParameterView extends View<ClimateParameter>, ViewType {
    Optional<? extends ClimateParameter> addUnits(long id, List<Long> unitIds, User currentUser);
}
