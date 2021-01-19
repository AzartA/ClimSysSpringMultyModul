package com.orioninc.training.view.impl;

import com.orioninc.training.view.api.ViewFacade;
import com.orioninc.training.view.api.ViewType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewFacadeImpl implements ViewFacade {
    private List<ViewType> views;

    @Autowired
    public ViewFacadeImpl(List<ViewType> views) {
        this.views = views;
    }

    public void setViews(List<ViewType> views) {
        this.views = views;
    }

    public <T extends ViewType> T getView(Class<T> type) {
        for (ViewType v : views) {
            if (type.isAssignableFrom(v.getServiceClass())) {
                return (T) v.get();
            }
        }
        throw new RuntimeException("view is absent today");
    }
}
