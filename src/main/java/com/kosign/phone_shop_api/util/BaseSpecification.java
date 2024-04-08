package com.kosign.phone_shop_api.util;

import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification<T, U> {

    public static final String wildcard = "%";

    public abstract Specification<T> getFilter(U request);

    public static String containLowerCase(String searchField) {

        return wildcard + searchField.toLowerCase() + wildcard;

    }
    public static String containUpperCase(String searchField) {

        return wildcard + searchField.toUpperCase() + wildcard;

    }
}
