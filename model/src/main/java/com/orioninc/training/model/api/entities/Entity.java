package com.orioninc.training.model.api.entities;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.MappedSuperclass;

public interface Entity {
    long getId();

    String getName();
}
