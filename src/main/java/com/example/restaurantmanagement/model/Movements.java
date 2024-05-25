package com.example.restaurantmanagement.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movements {
    private Integer id;
    private String type;
    private Double quantity;
    private Instant date;
    private Double quantityRemaining;
    private Integer idIngredientTemplate;

    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String QUANTITY = "quantity";
    public static final String DATE = "date";
    public static final String QUANTITY_REMAINING = "quantity_remaining";
    public static final String ID_INGREDIENT_TEMPLATE = "id_ingredient_template";
}
