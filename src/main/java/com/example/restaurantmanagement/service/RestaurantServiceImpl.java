package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Restaurant;
import com.example.restaurantmanagement.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> getAllRestaurants() throws SQLException {
        return restaurantRepository.getAllRestaurants();
    }

    @Override
    public Restaurant getRestaurantById(int id) throws SQLException {
        return restaurantRepository.getRestaurantById(id);
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) throws SQLException {
        return restaurantRepository.createRestaurant(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(int id, Restaurant restaurant) throws SQLException {
        return restaurantRepository.updateRestaurant(id, restaurant);
    }
}
