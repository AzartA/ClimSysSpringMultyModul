package com.orioninc.training.view.api;


import com.orioninc.training.model.api.entities.Entity;

public interface ViewType {
    Class<? extends Entity> getType();

    Class<? extends ViewType> getServiceClass();

    ViewType get();
}
