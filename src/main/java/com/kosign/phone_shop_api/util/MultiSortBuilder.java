package com.kosign.phone_shop_api.util;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiSortBuilder {

    private final List<Sort.Order> orders;

    public MultiSortBuilder() {
        this.orders = new ArrayList<>();
    }

    private String getProperty(String property){
        return switch (property){
            case "account_no" -> "accountNumber";
            case "bank_code" -> "bankCode";
            default -> property;
        };
    }

    public final MultiSortBuilder with(String sortDirection) {

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(sortDirection + ",");
        while (matcher.find()) {
            String property = matcher.group(1);
            String direction = matcher.group(3);
            orders.add(new Sort.Order(Sort.Direction.fromString(direction), getProperty(property)));
        }

        return this;
    }

    public List<Sort.Order> build() {
        if (orders.size() == 0)
            return Collections.emptyList();

        return orders;
    }
}
