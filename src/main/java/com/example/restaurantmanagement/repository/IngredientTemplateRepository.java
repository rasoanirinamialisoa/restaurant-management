package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.IngredientTemplate;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface IngredientTemplateRepository {
    List<IngredientTemplate> getAllIngredientTemplates() throws SQLException;
    IngredientTemplate getIngredientTemplateById(int id) throws SQLException;
    IngredientTemplate createIngredientTemplate(IngredientTemplate ingredientTemplate) throws SQLException;
    IngredientTemplate updateIngredientTemplate(int id, IngredientTemplate ingredientTemplate) throws SQLException;
}

