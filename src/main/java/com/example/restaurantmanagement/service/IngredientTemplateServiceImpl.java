package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.IngredientTemplate;
import com.example.restaurantmanagement.repository.IngredientTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class IngredientTemplateServiceImpl implements IngredientTemplateService {

    @Autowired
    private final IngredientTemplateRepository ingredientTemplateRepository;

    public IngredientTemplateServiceImpl(IngredientTemplateRepository ingredientTemplateRepository) {
        this.ingredientTemplateRepository = ingredientTemplateRepository;
    }

    @Override
    public List<IngredientTemplate> getAllIngredientTemplates() throws SQLException {
        return ingredientTemplateRepository.getAllIngredientTemplates();
    }

    @Override
    public IngredientTemplate getIngredientTemplateById(int id) throws SQLException {
        return ingredientTemplateRepository.getIngredientTemplateById(id);
    }

    @Override
    public IngredientTemplate createIngredientTemplate(IngredientTemplate ingredientTemplate) throws SQLException {
        return ingredientTemplateRepository.createIngredientTemplate(ingredientTemplate);
    }

    @Override
    public IngredientTemplate updateIngredientTemplate(int id, IngredientTemplate ingredientTemplate) throws SQLException {
        return ingredientTemplateRepository.updateIngredientTemplate(id, ingredientTemplate);
    }
}
