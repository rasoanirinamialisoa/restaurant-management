package com.example.restaurantmanagement.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Units {
    private int id;
    private String name;

    public static final String ID = "id";
    public static final String NAME = "name";
}
