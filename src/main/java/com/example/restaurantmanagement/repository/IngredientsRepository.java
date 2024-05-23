package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Ingredients;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public interface IngredientsRepository {
    List<Ingredients> getAllIngredients() throws SQLException;
    Ingredients getIngredientsById(int id) throws SQLException;
    Ingredients createIngredients(Ingredients ingredients) throws SQLException;
    Ingredients updateIngredientTemplate(int id, Ingredients ingredients) throws SQLException;
}
