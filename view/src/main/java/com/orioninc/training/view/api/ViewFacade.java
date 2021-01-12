package com.orioninc.training.view.api;

public interface ViewFacade {
    <T extends ViewType> T getView(Class<T> type);
}
