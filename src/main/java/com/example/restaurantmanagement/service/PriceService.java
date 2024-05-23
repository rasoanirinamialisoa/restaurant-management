package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Price;

import java.sql.SQLException;
import java.util.List;

public interface PriceService {
    List<Price> getAllPrices() throws SQLException;

    Price getPriceById(int id) throws SQLException;

    Price createPrice(Price price) throws SQLException;

    Price updatePrice(int id, Price price) throws SQLException;
}
