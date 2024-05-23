package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Ingredients;

import java.sql.SQLException;
import java.util.List;

public interface IngredientsService {
    List<Ingredients> getAllIngredients() throws SQLException;
    Ingredients getIngredientsById(int id) throws SQLException;
    Ingredients createIngredients(Ingredients ingredients) throws SQLException;
    Ingredients updateIngredients(int id, Ingredients ingredients) throws SQLException;

}
