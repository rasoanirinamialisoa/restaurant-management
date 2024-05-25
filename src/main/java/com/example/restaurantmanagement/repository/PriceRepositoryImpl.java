package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.config.ConnectDatabase;
import com.example.restaurantmanagement.model.Price;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.TimerTask;

import static com.example.restaurantmanagement.repository.SetParameter.setParameter;

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
            preparedStatement.setTimestamp(1, Timestamp.valueOf(String.valueOf(price.getDate())));
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
        StringJoiner query = new StringJoiner(", ", "UPDATE price SET ", " WHERE id = ?");

        if (price.getDate() != null) {
            query.add("date = ?");
        }
        if (price.getSellingPrice() != null) {
            query.add("selling_price = ?");
        }
        if (price.getCostPrice() != null) {
            query.add("cost_price = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int parameterIndex = 1;
            if (price.getDate() != null) {
                setParameter(preparedStatement, parameterIndex++, Timestamp.valueOf(String.valueOf(price.getDate())));
            }
            if (price.getSellingPrice() != null) {
                preparedStatement.setDouble(parameterIndex++, price.getSellingPrice());
            }
            if (price.getCostPrice() != null) {
                setParameter(preparedStatement, parameterIndex++, price.getCostPrice());
            }
            setParameter(preparedStatement, parameterIndex, id);

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
        price.setDate(Instant.from(resultSet.getTimestamp("date").toInstant()));
        price.setSellingPrice(resultSet.getDouble(Price.SELLING_PRICE));
        price.setCostPrice(resultSet.getDouble(Price.COST_PRICE));
        return price;
    }
}
