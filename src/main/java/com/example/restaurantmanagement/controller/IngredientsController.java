package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Ingredients;
import com.example.restaurantmanagement.service.IngredientsService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IngredientsController {

    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/ingredients")
    public List<Ingredients> getAllIngredients() throws SQLException {
        return ingredientsService.getAllIngredients();
    }

    @GetMapping("/ingredients/{id}")
    public Ingredients getIngredientsById(@PathVariable int id) throws SQLException {
        return ingredientsService.getIngredientsById(id);
    }

    @PostMapping("/ingredients")
    public Ingredients createIngredients(@RequestBody Ingredients ingredients) throws SQLException {
        return ingredientsService.createIngredients(ingredients);
    }

    @PutMapping("/ingredients/{id}")
    public Ingredients updateIngredients(@PathVariable int id, @RequestBody Ingredients ingredients) throws SQLException {
        return ingredientsService.updateIngredients(id, ingredients);
    }
}
