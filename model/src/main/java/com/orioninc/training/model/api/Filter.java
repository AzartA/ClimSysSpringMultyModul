package com.orioninc.training.model.api;

import com.orioninc.training.model.filters.Operator;

import java.util.List;

public interface Filter {
    String DELIMITER = ";";
    String getField();
    Operator getOperation();
    String getValue();
    List<String> getValueList();
}
