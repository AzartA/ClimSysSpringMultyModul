package com.orioninc.training.model_api.view;


import com.orioninc.training.model_api.model.Entity;

public interface ViewType {
    Class<? extends Entity> getType();

    Class<? extends ViewType> getServiceClass();

    ViewType get();
}
