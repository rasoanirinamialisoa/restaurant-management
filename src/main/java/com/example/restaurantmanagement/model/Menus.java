package com.example.restaurantmanagement.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menus {
    private int id;
    private String name;
    private double price;
    private int idRestaurant;
    private int idPrice;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String ID_RESTAURANT = "id_restaurant";
    public static final String ID_PRICE = "id_price";
}
