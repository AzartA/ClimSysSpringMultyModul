package com.orioninc.training.view.impl;

public interface ViewFacade {
    <T extends ViewType> T getView(Class<T> type);
}
