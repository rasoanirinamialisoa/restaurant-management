package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Price;
import com.example.restaurantmanagement.service.PriceService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices")
    public List<Price> getAllPrices() throws SQLException {
        return priceService.getAllPrices();
    }

    @GetMapping("/prices/{id}")
    public Price getPriceById(@PathVariable int id) throws SQLException {
        return priceService.getPriceById(id);
    }

    @PostMapping("/prices")
    public Price createPrice(@RequestBody Price price) throws SQLException {
        return priceService.createPrice(price);
    }

    @PutMapping("/prices/{id}")
    public Price updatePrice(@PathVariable int id, @RequestBody Price price) throws SQLException {
        return priceService.updatePrice(id, price);
    }
}
