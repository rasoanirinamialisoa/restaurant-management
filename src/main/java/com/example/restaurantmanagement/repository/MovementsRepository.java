package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Movements;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public interface MovementsRepository {
    List<Movements> getAllMovements() throws SQLException;

    Movements getMovementById(int id) throws SQLException;

    Movements createMovement(Movements movement) throws SQLException;

    Movements updateMovement(int id, Movements movement) throws SQLException;

    Movements findLatestMovementByIngredientTemplate(Integer idIngredientTemplate) throws SQLException;
}
