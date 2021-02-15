package com.orioninc.training.view.api;

import com.orioninc.training.model.api.entities.SensorType;
import com.orioninc.training.model.api.entities.User;

import java.util.List;
import java.util.Optional;

public interface SensorTypeView extends View<SensorType>, ViewType {
    Optional<? extends SensorType> addParams(long id, List<Long> paramsIds, User currentUser);
}
