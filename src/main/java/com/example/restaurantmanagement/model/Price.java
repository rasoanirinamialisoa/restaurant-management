package com.example.restaurantmanagement.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Price {
    private Integer id;
    private Instant date;
    private Double sellingPrice;
    private Double costPrice;

    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String SELLING_PRICE = "selling_price";
    public static final String COST_PRICE = "cost_price";
}
