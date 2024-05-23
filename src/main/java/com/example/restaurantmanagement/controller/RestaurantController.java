package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Restaurant;
import com.example.restaurantmanagement.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() throws SQLException {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable int id) throws SQLException {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping("/restaurants")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) throws SQLException {
        return restaurantService.createRestaurant(restaurant);
    }

    @PutMapping("/restaurants/{id}")
    public Restaurant updateRestaurant(@PathVariable int id, @RequestBody Restaurant restaurant) throws SQLException {
        return restaurantService.updateRestaurant(id, restaurant);
    }
}
