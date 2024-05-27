package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Movements;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

public interface MovementsService {
    List<Movements> getAllMovements() throws SQLException;
    Movements getMovementsById(int id) throws SQLException;
    Movements createMovements(Movements movements) throws SQLException;
    Movements updateMovements(int id, Movements movements) throws SQLException;
    Movements addStock(Movements movements) throws SQLException;
    Movements consumeStock(Movements movements) throws SQLException;
}
