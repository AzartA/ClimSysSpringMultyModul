package com.orioninc.training.model.filters;


import com.orioninc.training.model.api.Filter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterImpl implements Filter {
    private String field;
    private Operator operation;
    private String value;

    public FilterImpl(Filter filter) {
        this.field = filter.getField();
        this.operation = filter.getOperation();
        this.value = filter.getValue();
    }

    public List<String> getValueList() {
        return Arrays.asList(value.split(DELIMITER));
    }
}
