package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.IngredientTemplate;
import java.sql.SQLException;
import java.util.List;

public interface IngredientTemplateService {
    List<IngredientTemplate> getAllIngredientTemplates() throws SQLException;
    IngredientTemplate getIngredientTemplateById(int id) throws SQLException;
    IngredientTemplate createIngredientTemplate(IngredientTemplate ingredientTemplate) throws SQLException;
    IngredientTemplate updateIngredientTemplate(int id, IngredientTemplate ingredientTemplate) throws SQLException;
}
