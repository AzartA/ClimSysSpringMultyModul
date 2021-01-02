package com.orioninc.training.model_api.view;

public interface ViewFacade {
    <T extends ViewType> T getView(Class<T> type);
}
