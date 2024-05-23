package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Price;
import com.example.restaurantmanagement.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> getAllPrices() throws SQLException {
        return priceRepository.getAllPrices();
    }

    @Override
    public Price getPriceById(int id) throws SQLException {
        return priceRepository.getPriceById(id);
    }

    @Override
    public Price createPrice(Price price) throws SQLException {
        return priceRepository.createPrice(price);
    }

    @Override
    public Price updatePrice(int id, Price price) throws SQLException {
        return priceRepository.updatePrice(id, price);
    }
}
