package com.orioninc.training.repo.impl;

import com.orioninc.training.model.api.Filter;
import com.orioninc.training.repo.api.GenericSpecification;
import org.jetbrains.annotations.NotNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GenericSpecificationImpl<T> implements GenericSpecification<T> {

    private Filter filter;

    public GenericSpecificationImpl(Filter filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<T> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder builder) {
        switch (filter.getOperation()) {
            case EQUAL:
                return builder.equal(root.get(filter.getField()), filter.getValue());
            case NON_EQUAL:
                return builder.notEqual(root.get(filter.getField()), filter.getValue());
            case GREATER:
                return builder.greaterThan(root.get(filter.getField()), filter.getValue());
            case LESS:
                return builder.lessThan(root.get(filter.getField()), filter.getValue());
            case GREATER_OR_EQUAL:
                return builder.greaterThanOrEqualTo(root.get(filter.getField()), filter.getValue());
            case LESS_OR_EQUAL:
                return builder.lessThanOrEqualTo(root.get(filter.getField()), filter.getValue());
            case LIKE:
                return builder.like(root.get(filter.getField()), "%" + filter.getValue() + "%");
            case NOT_LIKE:
                return builder.notLike(root.get(filter.getField()), "%" + filter.getValue() + "%");
            case IN:
                return (root.<String> get(filter.getField())).in(filter.getValueList());
            case NOT_IN:
                return builder.not((root.<String> get(filter.getField())).in(filter.getValueList()));
            default:
                return null;
        }
    }


}
