package com.example.restaurantmanagement.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ingredients {
    private Integer id;
    private Integer idIngredientTemplate;
    private Integer idMenus;
    private Double quantityNecessary;

    public static final String ID = "id";
    public static final String ID_INGREDIENT_TEMPLATE = "id_ingredient_template";
    public static final String ID_MENUS = "id_menus";
    public static final String QUANTITY_NECESSARY = "quantity_necessary";
}
