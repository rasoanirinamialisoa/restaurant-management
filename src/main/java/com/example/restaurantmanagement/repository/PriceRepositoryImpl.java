package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.config.ConnectDatabase;
import com.example.restaurantmanagement.model.Price;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
    private final Connection connection = connectDatabase.getConnection();

    public PriceRepositoryImpl() throws SQLException {
    }

    @Override
    public List<Price> getAllPrices() throws SQLException {
        List<Price> prices = new ArrayList<>();
        String query = "SELECT * FROM price";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Price price = mapResultSetToPrice(resultSet);
                prices.add(price);
            }
        }
        return prices;
    }



    @Override
    public Price getPriceById(int id) throws SQLException {
        String query = "SELECT * FROM price WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToPrice(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Price createPrice(Price price) throws SQLException {
        String query = "INSERT INTO price (date, selling_price, cost_price) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, price.getDate());
            preparedStatement.setDouble(2, price.getSellingPrice());
            preparedStatement.setDouble(3, price.getCostPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return price;
            }
        }
        return null;
    }

    @Override
    public Price updatePrice(int id, Price price) throws SQLException {
        String query = "UPDATE price SET date = ?, selling_price = ?, cost_price = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, price.getDate());
            preparedStatement.setDouble(2, price.getSellingPrice());
            preparedStatement.setDouble(3, price.getCostPrice());
            preparedStatement.setInt(4, id);

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return price;
            }
        }
        return null;
    }

    private Price mapResultSetToPrice(ResultSet resultSet) throws SQLException {
        Price price = new Price();
        price.setId(resultSet.getInt(Price.ID));
        price.setDate(resultSet.getDate(Price.DATE));
        price.setSellingPrice(resultSet.getDouble(Price.SELLING_PRICE));
        price.setCostPrice(resultSet.getDouble(Price.COST_PRICE));
        return price;
    }
}
