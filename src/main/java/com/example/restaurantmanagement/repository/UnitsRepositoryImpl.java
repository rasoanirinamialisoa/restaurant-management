package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.config.ConnectDatabase;
import com.example.restaurantmanagement.model.Units;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.restaurantmanagement.repository.SetParameter.setParameter;

@Repository
public class UnitsRepositoryImpl implements UnitsRepository {

    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    public UnitsRepositoryImpl() throws SQLException {
    }

    @Override
    public List<Units> getAllUnits() throws SQLException {
        List<Units> unitsList = new ArrayList<>();
        String query = "SELECT * FROM units";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Units units = mapResultSetToUnits(resultSet);
                unitsList.add(units);
            }
        }
        return unitsList;
    }

    @Override
    public Units getUnitsById(int id) throws SQLException {
        String query = "SELECT * FROM units WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUnits(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Units createUnits(Units units) throws SQLException {
        String query = "INSERT INTO units (name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, units.getName());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return units;
            }
        }
        return null;
    }

    @Override
    public Units updateUnits(int id, Units units) throws SQLException {
        String query = "UPDATE units SET name = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameter(preparedStatement, 1, units.getName());
            setParameter(preparedStatement, 2, id);

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return units;
            }
        }
        return null;
    }


    private Units mapResultSetToUnits(ResultSet resultSet) throws SQLException {
        Units units = new Units();
        units.setId(resultSet.getInt(Units.ID));
        units.setName(resultSet.getString(Units.NAME));
        return units;
    }
}
