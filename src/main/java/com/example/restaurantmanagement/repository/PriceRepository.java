package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Price;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface PriceRepository {
    List<Price> getAllPrices() throws SQLException;
    Price getPriceById(int id) throws SQLException;
    Price createPrice(Price price) throws SQLException;
    Price updatePrice(int id, Price price) throws SQLException;
}
