package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Movements;
import com.example.restaurantmanagement.config.ConnectDatabase;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import static com.example.restaurantmanagement.repository.SetParameter.setParameter;

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
            preparedStatement.setTimestamp(3, Timestamp.from(movement.getDate()));
            preparedStatement.setDouble(4, movement.getQuantityRemaining());
            preparedStatement.setInt(5, movement.getIdIngredientTemplate());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        movement.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating movement failed, no ID obtained.");
                    }
                }
            }
            return movement;
        }
    }

    @Override
    public Movements findLatestMovementByIngredientTemplate(Integer idIngredientTemplate) throws SQLException {
        String query = "SELECT * FROM movements WHERE id_ingredient_template = ? ORDER BY date DESC LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idIngredientTemplate);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Movements movements = new Movements();
                    movements.setId(resultSet.getInt("id"));
                    movements.setType(resultSet.getString("type"));
                    movements.setQuantity(resultSet.getDouble("quantity"));
                    movements.setDate(resultSet.getTimestamp("date").toInstant());
                    movements.setQuantityRemaining(resultSet.getDouble("quantity_remaining"));
                    movements.setIdIngredientTemplate(resultSet.getInt("id_ingredient_template"));
                    return movements;
                }
            }
        }
        return null;
    }

    @Override
    public Movements updateMovement(int id, Movements movement) throws SQLException {
        StringJoiner query = new StringJoiner(", ", "UPDATE movements SET ", " WHERE id = ?");

        if (movement.getType() != null) {
            query.add("type = ?");
        }
        if (movement.getQuantity() != null) {
            query.add("quantity = ?");
        }
        if (movement.getDate() != null) {
            query.add("date = ?");
        }
        if (movement.getQuantityRemaining() != null) {
            query.add("quantity_remaining = ?");
        }
        if (movement.getIdIngredientTemplate() != null) {
            query.add("id_ingredient_template = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int parameterIndex = 1;
            if (movement.getType() != null) {
                setParameter(preparedStatement, parameterIndex++,movement.getType());
            }
            if (movement.getQuantity() != null) {
                setParameter(preparedStatement, parameterIndex++, movement.getQuantity());
            }
            if (movement.getDate() != null) {
                preparedStatement.setTimestamp(parameterIndex++, Timestamp.from(movement.getDate()));
            }
            if (movement.getQuantityRemaining() != null) {
                setParameter(preparedStatement, parameterIndex++, movement.getQuantityRemaining());
            }
            if (movement.getIdIngredientTemplate() != null) {
                setParameter(preparedStatement, parameterIndex++, movement.getIdIngredientTemplate());
            }
            setParameter(preparedStatement, parameterIndex, id);

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
        movement.setDate(Instant.from(resultSet.getTimestamp("date").toInstant()));
        movement.setQuantityRemaining(resultSet.getDouble("quantity_remaining"));
        movement.setIdIngredientTemplate(resultSet.getInt("id_ingredient_template"));
        return movement;
    }
}
