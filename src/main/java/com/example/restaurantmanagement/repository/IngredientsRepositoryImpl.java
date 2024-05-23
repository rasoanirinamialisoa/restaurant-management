package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.config.ConnectDatabase;
import com.example.restaurantmanagement.model.Ingredients;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class IngredientsRepositoryImpl implements IngredientsRepository {

    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    public IngredientsRepositoryImpl() throws SQLException {
    }

    @Override
    public List<Ingredients> getAllIngredients() throws SQLException {
        List<Ingredients> ingredientsList = new ArrayList<>();
        String query = "SELECT * FROM ingredients";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Ingredients ingredients = mapResultSetToIngredients(resultSet);
                ingredientsList.add(ingredients);
            }
        }
        return ingredientsList;
    }

    @Override
    public Ingredients getIngredientsById(int id) throws SQLException {
        String query = "SELECT * FROM ingredients WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToIngredients(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Ingredients createIngredients(Ingredients ingredients) throws SQLException {
        String query = "INSERT INTO ingredients (id_ingredient_template, id_menus, quantity_necessary) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ingredients.getIdIngredientTemplate());
            preparedStatement.setInt(2, ingredients.getIdMenus());
            preparedStatement.setDouble(3, ingredients.getQuantityNecessary());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return ingredients;
            }
        }
        return null;
    }

    @Override
    public Ingredients updateIngredientTemplate(int id, Ingredients ingredients) throws SQLException {
        String query = "UPDATE ingredients SET id_ingredient_template = ?, id_menus = ?, quantity_necessary = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ingredients.getIdIngredientTemplate());
            preparedStatement.setInt(2, ingredients.getIdMenus());
            preparedStatement.setDouble(3, ingredients.getQuantityNecessary());
            preparedStatement.setInt(4, id);

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return ingredients;
            }
        }
        return null;
    }

    private Ingredients mapResultSetToIngredients(ResultSet resultSet) throws SQLException {
        Ingredients ingredients = new Ingredients();
        ingredients.setId(resultSet.getInt(Ingredients.ID));
        ingredients.setIdIngredientTemplate(resultSet.getInt(Ingredients.ID_INGREDIENT_TEMPLATE));
        ingredients.setIdMenus(resultSet.getInt(Ingredients.ID_MENUS));
        ingredients.setQuantityNecessary(resultSet.getDouble(Ingredients.QUANTITY_NECESSARY));
        return ingredients;
    }
}
