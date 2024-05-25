package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Movements;

import java.sql.SQLException;
import java.util.List;

public interface MovementsService {
    List<Movements> getAllMovements() throws SQLException;
    Movements getMovementsById(int id) throws SQLException;
    Movements createMovements(Movements movements) throws SQLException;
    Movements updateMovements(int id, Movements movements) throws SQLException;
    Movements updateQuantityRemaining(int id, Movements movements) throws SQLException;

}
