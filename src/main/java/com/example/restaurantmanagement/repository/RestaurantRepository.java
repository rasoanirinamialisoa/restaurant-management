package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface RestaurantRepository {
    List<Restaurant> getAllRestaurants() throws SQLException;

    Restaurant getRestaurantById(int id) throws SQLException;

    Restaurant createRestaurant(Restaurant restaurant) throws SQLException;

    Restaurant updateRestaurant(int id, Restaurant restaurant) throws SQLException;
}
