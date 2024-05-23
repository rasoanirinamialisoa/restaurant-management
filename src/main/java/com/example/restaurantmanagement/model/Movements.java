package com.example.restaurantmanagement.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movements {
    private int id;
    private String type;
    private double quantity;
    private java.sql.Timestamp date;
    private double quantityRemaining;
    private int idIngredientTemplate;

    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String QUANTITY = "quantity";
    public static final String DATE = "date";
    public static final String QUANTITY_REMAINING = "quantity_remaining";
    public static final String ID_INGREDIENT_TEMPLATE = "id_ingredient_template";
}
