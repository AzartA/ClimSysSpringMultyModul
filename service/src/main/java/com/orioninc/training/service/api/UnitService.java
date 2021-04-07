package com.orioninc.training.service.api;

import com.orioninc.training.model.api.entities.Unit;

import java.util.List;

public interface UnitService {
    List<Unit> getAll();
    Unit create(Unit unit);
    Unit update (Unit unit);

}
