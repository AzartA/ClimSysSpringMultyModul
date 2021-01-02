package com.orioninc.training.model_api.view;

public interface FilterParam {
    String getField();

    String getCond();

    String getValue();

    void setField(String field);

    void setCond(String cond);

    void setValue(String value);
}
