package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Movements;
import com.example.restaurantmanagement.service.MovementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    private MovementsService movementsService;

    @PostMapping("/addStock")
    public ResponseEntity<?> addStock(@RequestBody Movements movements) throws SQLException {
        Movements savedMovement = movementsService.addStock(movements);
        return ResponseEntity.ok(savedMovement);
    }

    @PostMapping("/consumeStock")
    public ResponseEntity<?> consumeStock(@RequestBody Movements movement) throws SQLException {
        Movements updatedMovement = movementsService.consumeStock(movement);
        return ResponseEntity.ok(updatedMovement);
    }
}