package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.config.ConnectDatabase;
import com.example.restaurantmanagement.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    public RestaurantRepositoryImpl() throws SQLException {
    }

    @Override
    public List<Restaurant> getAllRestaurants() throws SQLException {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT * FROM restaurant";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Restaurant restaurant = mapResultSetToRestaurant(resultSet);
                restaurants.add(restaurant);
            }
        }
        return restaurants;
    }

    @Override
    public Restaurant getRestaurantById(int id) throws SQLException {
        String query = "SELECT * FROM restaurant WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToRestaurant(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) throws SQLException {
        String query = "INSERT INTO restaurant (location) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, restaurant.getLocation());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return restaurant;
            }
        }
        return null;
    }

    @Override
    public Restaurant updateRestaurant(int id, Restaurant restaurant) throws SQLException {
        String query = "UPDATE restaurant SET location = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, restaurant.getLocation());
            preparedStatement.setInt(2, id);

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return restaurant;
            }
        }
        return null;
    }

    private Restaurant mapResultSetToRestaurant(ResultSet resultSet) throws SQLException {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(resultSet.getInt(Restaurant.ID));
        restaurant.setLocation(resultSet.getString(Restaurant.LOCATION));
        return restaurant;
    }
}
