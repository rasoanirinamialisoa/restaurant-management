package com.example.restaurantmanagement.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IngredientTemplate {
    private int id;
    private String name;
    private double price;
    private int idUnit;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String ID_UNIT = "id_unit";
}
