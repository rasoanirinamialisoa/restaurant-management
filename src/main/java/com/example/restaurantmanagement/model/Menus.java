package com.example.restaurantmanagement.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menus {
    private Integer id;
    private String name;
    private Integer idRestaurant;
    private Integer idPrice;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ID_RESTAURANT = "id_restaurant";
    public static final String ID_PRICE = "id_price";
}
