package com.orioninc.training.view.api;

import com.orioninc.training.model.api.entities.Sensor;
import com.orioninc.training.model.api.entities.User;

import java.util.List;
import java.util.Optional;

public interface SensorView extends View<Sensor>, ViewType {
    Optional<? extends Sensor> setSensorType(long id, long typeId, User currentUser);

    Optional<? extends Sensor> setLocation(long id, long locationId, User currentUser);

    Optional<? extends Sensor> addUsers(long id, List<Long> userIds, User currentUser);

    Optional<? extends Sensor> setXY(long id, long x, long y, User currentUser);
}
