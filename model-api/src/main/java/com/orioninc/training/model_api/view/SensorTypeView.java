package com.orioninc.training.model_api.view;

import com.orioninc.training.model_api.model.SensorType;
import com.orioninc.training.model_api.model.User;

import java.util.List;
import java.util.Optional;

public interface SensorTypeView extends View<SensorType>, ViewType {
    Optional<? extends SensorType> addParams(long id, List<Long> paramsIds, User currentUser);
}
