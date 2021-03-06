package com.orioninc.training.model_api.view;

import com.orioninc.training.model_api.model.Location;
import com.orioninc.training.model_api.model.User;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public interface LocationView extends View<Location>, ViewType {

    Optional<Object> getPlan(long id, OutputStream outputStream, User currentUser);

    long setPlan(long id, InputStream inputStream, String type, User currentUser);

    Optional<? extends Location> deletePlan(long id, User currentUser);

}
