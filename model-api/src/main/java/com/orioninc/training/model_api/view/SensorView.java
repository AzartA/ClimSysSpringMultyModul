package com.orioninc.training.model_api.view;

import com.orioninc.training.model_api.model.Sensor;
import com.orioninc.training.model_api.model.User;

import java.util.List;
import java.util.Optional;

public interface SensorView extends View<Sensor>, ViewType {
    Optional<? extends Sensor> setSensorType(long id, long typeId, User currentUser);

    Optional<? extends Sensor> setLocation(long id, long locationId, User currentUser);

    Optional<? extends Sensor> addUsers(long id, List<Long> userIds, User currentUser);

    Optional<? extends Sensor> setXY(long id, long x, long y, User currentUser);
}
