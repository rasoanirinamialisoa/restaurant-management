package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.IngredientTemplate;
import com.example.restaurantmanagement.service.IngredientTemplateService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IngredientTemplateController {

    private final IngredientTemplateService ingredientTemplateService;

    public IngredientTemplateController(IngredientTemplateService ingredientTemplateService) {
        this.ingredientTemplateService = ingredientTemplateService;
    }

    @GetMapping("/ingredient-templates")
    public List<IngredientTemplate> getAllIngredientTemplates() throws SQLException {
        return ingredientTemplateService.getAllIngredientTemplates();
    }

    @GetMapping("/ingredient-templates/{id}")
    public IngredientTemplate getIngredientTemplateById(@PathVariable int id) throws SQLException {
        return ingredientTemplateService.getIngredientTemplateById(id);
    }

    @PostMapping("/ingredient-templates")
    public IngredientTemplate createIngredientTemplate(@RequestBody IngredientTemplate ingredientTemplate) throws SQLException {
        return ingredientTemplateService.createIngredientTemplate(ingredientTemplate);
    }

    @PutMapping("/ingredient-templates/{id}")
    public IngredientTemplate updateIngredientTemplate(@PathVariable int id, @RequestBody IngredientTemplate ingredientTemplate) throws SQLException {
        return ingredientTemplateService.updateIngredientTemplate(id, ingredientTemplate);
    }
}
