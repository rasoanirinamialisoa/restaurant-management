package com.example.restaurantmanagement.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Price {
    private int id;
    private java.sql.Date date;
    private double sellingPrice;
    private double costPrice;

    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String SELLING_PRICE = "selling_price";
    public static final String COST_PRICE = "cost_price";
}
