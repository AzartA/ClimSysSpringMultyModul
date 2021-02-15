package com.orioninc.training.model.api.entities;

import javax.persistence.MappedSuperclass;

public interface Entity {
    long getId();

    String getName();
}
