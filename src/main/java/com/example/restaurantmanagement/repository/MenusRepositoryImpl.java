package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.config.ConnectDatabase;
import com.example.restaurantmanagement.model.Menus;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static com.example.restaurantmanagement.repository.SetParameter.setParameter;

@Repository
public class MenusRepositoryImpl implements MenusRepository {

    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    public MenusRepositoryImpl() throws SQLException {
    }

    @Override
    public List<Menus> getAllMenus() throws SQLException {
        List<Menus> menusList = new ArrayList<>();
        String query = "SELECT * FROM menus";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Menus menus = mapResultSetToMenus(resultSet);
                menusList.add(menus);
            }
        }
        return menusList;
    }

    @Override
    public Menus getMenusById(int id) throws SQLException {
        String query = "SELECT * FROM menus WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToMenus(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Menus createMenus(Menus menus) throws SQLException {
        String query = "INSERT INTO menus (name, id_restaurant, id_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, menus.getName());
            preparedStatement.setInt(2, menus.getIdRestaurant());
            preparedStatement.setInt(3, menus.getIdPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return menus;
            }
        }
        return null;
    }

    @Override
    public Menus updateMenus(int id, Menus menus) throws SQLException {
        StringJoiner query = new StringJoiner(", ", "UPDATE menus SET ", " WHERE id = ?");

        if (menus.getName() != null) {
            query.add("name = ?");
        }
        if (menus.getIdRestaurant() != null) {
            query.add("id_restaurant = ?");
        }
        if (menus.getIdPrice() != null) {
            query.add("id_price = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int parameterIndex = 1;
            if (menus.getName() != null) {
                setParameter(preparedStatement, parameterIndex++, menus.getName());
            }
            if (menus.getIdRestaurant() != null) {
                setParameter(preparedStatement, parameterIndex++, menus.getIdRestaurant());
            }
            if (menus.getIdPrice() != null) {
                setParameter(preparedStatement, parameterIndex++, menus.getIdPrice());
            }
            setParameter(preparedStatement, parameterIndex, id);

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return menus;
            }
        }
        return null;
    }

    private Menus mapResultSetToMenus(ResultSet resultSet) throws SQLException {
        Menus menus = new Menus();
        menus.setId(resultSet.getInt(Menus.ID));
        menus.setName(resultSet.getString(Menus.NAME));
        menus.setIdRestaurant(resultSet.getInt(Menus.ID_RESTAURANT));
        menus.setIdPrice(resultSet.getInt(Menus.ID_PRICE));
        return menus;
    }
}
