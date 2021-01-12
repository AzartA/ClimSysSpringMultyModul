package com.orioninc.training.model.entities;

import java.text.SimpleDateFormat;

public interface Measuring extends Entity {
    SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

    java.sql.Timestamp getTimestamp();

    Entity getSensor();

    Entity getParameter();

    float getValue();
}
