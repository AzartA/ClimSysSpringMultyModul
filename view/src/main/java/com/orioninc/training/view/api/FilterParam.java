package com.orioninc.training.view.api;

public interface FilterParam {
    String getField();

    String getCond();

    String getValue();

    void setField(String field);

    void setCond(String cond);

    void setValue(String value);
}
