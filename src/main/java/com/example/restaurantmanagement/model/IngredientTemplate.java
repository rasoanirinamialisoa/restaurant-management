package com.example.restaurantmanagement.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IngredientTemplate {
    private Integer id;
    private String name;
    private Double price;
    private Integer idUnit;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String ID_UNIT = "id_unit";
}
