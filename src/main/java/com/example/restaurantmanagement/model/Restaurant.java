package com.example.restaurantmanagement.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Restaurant {
    private int id;
    private String location;

    public static final String ID = "id";
    public static final String LOCATION = "location";
}
