package com.orioninc.training.model.filters;

public enum Operator {
    EQUAL("="),
    NON_EQUAL("!="),
    LESS("<"),
    LESS_OR_EQUAL("<="),
    GREATER_OR_EQUAL(">="),
    GREATER(">"),
    IN(":"),
    NOT_IN("!:"),
    LIKE("~"),
    NOT_LIKE("!~");
    private final String presentation;

    Operator(String presentation) {
        this.presentation = presentation;
    }

    public static Operator getOperator(String rawOper){
        for(Operator op : Operator.values()){
            if(op.presentation.equals(rawOper)){
                return op;
            }
        }
        throw new IllegalArgumentException("Unsupported Filter Operator: "+rawOper);
    }
}
