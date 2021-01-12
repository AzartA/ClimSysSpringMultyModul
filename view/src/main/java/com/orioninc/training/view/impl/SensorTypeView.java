package com.orioninc.training.view.impl;

import com.orioninc.training.model.entities.SensorType;
import com.orioninc.training.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface SensorTypeView extends View<SensorType>, ViewType {
    Optional<? extends SensorType> addParams(long id, List<Long> paramsIds, User currentUser);
}
