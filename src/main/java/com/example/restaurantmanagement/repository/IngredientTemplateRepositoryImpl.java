package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.IngredientTemplate;
import com.example.restaurantmanagement.config.ConnectDatabase;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static com.example.restaurantmanagement.repository.SetParameter.setParameter;

@Repository
public class IngredientTemplateRepositoryImpl implements IngredientTemplateRepository {
    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    public IngredientTemplateRepositoryImpl() throws SQLException {
    }

    @Override
    public List<IngredientTemplate> getAllIngredientTemplates() throws SQLException {
        List<IngredientTemplate> ingredientTemplates = new ArrayList<>();
        String query = "SELECT * FROM ingredient_template";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                IngredientTemplate ingredientTemplate = mapResultSetToIngredientTemplate(resultSet);
                ingredientTemplates.add(ingredientTemplate);
            }
        }
        return ingredientTemplates;
    }

    @Override
    public IngredientTemplate getIngredientTemplateById(int id) throws SQLException {
        String query = "SELECT * FROM ingredient_template WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToIngredientTemplate(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public IngredientTemplate createIngredientTemplate(IngredientTemplate ingredientTemplate) throws SQLException {
        String query = "INSERT INTO ingredient_template (name, price, id_unit) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, ingredientTemplate.getName());
            preparedStatement.setDouble(2, ingredientTemplate.getPrice());
            preparedStatement.setInt(3, ingredientTemplate.getIdUnit());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return ingredientTemplate;
            } else {
                throw new SQLException("Failed to insert ingredient template into database.");
            }
        }
    }

    @Override
    public IngredientTemplate updateIngredientTemplate(int id, IngredientTemplate ingredientTemplate) throws SQLException {
        StringJoiner query = new StringJoiner(", ", "UPDATE ingredient_template SET ", " WHERE id = ?");

        if (ingredientTemplate.getName() != null) {
            query.add("name = ?");
        }
        if (ingredientTemplate.getPrice() != null) {
            query.add("price = ?");
        }
        if (ingredientTemplate.getIdUnit() != null) {
            query.add("id_unit = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int parameterIndex = 1;
            if (ingredientTemplate.getName() != null) {
                setParameter(preparedStatement, parameterIndex++, ingredientTemplate.getName());
            }
            if (ingredientTemplate.getPrice() != null) {
                setParameter(preparedStatement, parameterIndex++, ingredientTemplate.getPrice());
            }
            if (ingredientTemplate.getIdUnit() != null) {
                setParameter(preparedStatement, parameterIndex++, ingredientTemplate.getIdUnit());
            }
            setParameter(preparedStatement, parameterIndex, id);

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return ingredientTemplate;
            }
        }
        return null;
    }

    private IngredientTemplate mapResultSetToIngredientTemplate(ResultSet resultSet) throws SQLException {
        IngredientTemplate ingredientTemplate = new IngredientTemplate();
        ingredientTemplate.setId(resultSet.getInt("id"));
        ingredientTemplate.setName(resultSet.getString("name"));
        ingredientTemplate.setPrice(resultSet.getDouble("price"));
        ingredientTemplate.setIdUnit(resultSet.getInt("id_unit"));
        return ingredientTemplate;
    }
}
