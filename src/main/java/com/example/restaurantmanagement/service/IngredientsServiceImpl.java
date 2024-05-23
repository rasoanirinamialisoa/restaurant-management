package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Ingredients;
import com.example.restaurantmanagement.repository.IngredientsRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class IngredientsServiceImpl implements IngredientsService {

    @Autowired
    private final IngredientsRepository ingredientsRepository;

    public IngredientsServiceImpl(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }


    @Override
    public List<Ingredients> getAllIngredients() throws SQLException {
        return ingredientsRepository.getAllIngredients();
    }

    @Override
    public Ingredients getIngredientsById(int id) throws SQLException {
        return ingredientsRepository.getIngredientsById(id);
    }

    @Override
    public Ingredients createIngredients(Ingredients ingredients) throws SQLException {
        return ingredientsRepository.createIngredients(ingredients);
    }

    @Override
    public Ingredients updateIngredients(int id, Ingredients ingredients) throws SQLException {
        return ingredientsRepository.updateIngredientTemplate(id, ingredients);
    }
}
