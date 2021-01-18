package com.orioninc.training.model.entities;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public interface Measuring extends Entity {
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.MEDIUM);

    OffsetDateTime getTimestamp();

    Entity getSensor();

    Entity getParameter();

    float getValue();
}
