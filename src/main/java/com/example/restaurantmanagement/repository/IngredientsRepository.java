package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Ingredients;

import java.sql.SQLException;
import java.util.List;

public interface IngredientsRepository {
    List<Ingredients> getAllIngredients() throws SQLException;
    Ingredients getIngredientById(int id) throws SQLException;
    Ingredients createIngredient(Ingredients ingredients) throws SQLException;
    Ingredients updateIngredient(int id, Ingredients ingredients) throws SQLException;
}
