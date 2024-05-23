package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Movements;
import com.example.restaurantmanagement.config.ConnectDatabase;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovementsRepositoryImpl implements MovementsRepository {
    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    public MovementsRepositoryImpl() throws SQLException {
    }

    @Override
    public List<Movements> getAllMovements() throws SQLException {
        List<Movements> movements = new ArrayList<>();
        String query = "SELECT * FROM movements";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Movements movement = mapResultSetToMovement(resultSet);
                movements.add(movement);
            }
        }
        return movements;
    }

    @Override
    public Movements getMovementById(int id) throws SQLException {
        String query = "SELECT * FROM movements WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToMovement(resultSet);
                }
            }
        }
        return null;
    }


    @Override
    public Movements createMovement(Movements movement) throws SQLException {
        String query = "INSERT INTO movements (type, quantity, date, quantity_remaining, id_ingredient_template) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, movement.getType());
            preparedStatement.setDouble(2, movement.getQuantity());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(String.valueOf(movement.getDate())));
            preparedStatement.setDouble(4, movement.getQuantityRemaining());
            preparedStatement.setInt(5, movement.getIdIngredientTemplate());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return movement;
            } else {
                throw new SQLException("Failed to insert movement into database.");
            }
        }
    }

    @Override
    public Movements updateMovement(int id, Movements movement) throws SQLException {
        String query = "UPDATE movements SET type = ?, quantity = ?, date = ?, quantity_remaining = ?, id_ingredient_template = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, movement.getType());
            preparedStatement.setDouble(2, movement.getQuantity());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(String.valueOf(movement.getDate())));
            preparedStatement.setDouble(4, movement.getQuantityRemaining());
            preparedStatement.setInt(5, movement.getIdIngredientTemplate());
            preparedStatement.setInt(6, id);

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return movement;
            }
        }
        return null;
    }

    private Movements mapResultSetToMovement(ResultSet resultSet) throws SQLException {
        Movements movement = new Movements();
        movement.setId(resultSet.getInt("id"));
        movement.setType(resultSet.getString("type"));
        movement.setQuantity(resultSet.getDouble("quantity"));
        movement.setDate(Instant.from(resultSet.getTimestamp("date").toLocalDateTime()));
        movement.setQuantityRemaining(resultSet.getDouble("quantity_remaining"));
        movement.setIdIngredientTemplate(resultSet.getInt("id_ingredient_template"));
        return movement;
    }
}
