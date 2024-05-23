package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Restaurant;
import java.sql.SQLException;
import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAllRestaurants() throws SQLException;
    Restaurant getRestaurantById(int id) throws SQLException;
    Restaurant createRestaurant(Restaurant restaurant) throws SQLException;
    Restaurant updateRestaurant(int id, Restaurant restaurant) throws SQLException;
}
