package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Movements;
import com.example.restaurantmanagement.repository.MovementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
@Service
public class MovementsServiceImpl implements MovementsService {
    @Autowired
    private final MovementsRepository movementsRepository;

    public MovementsServiceImpl(MovementsRepository movementsRepository) {
        this.movementsRepository = movementsRepository;
    }

    @Override
    public List<Movements> getAllMovements() throws SQLException {
        return movementsRepository.getAllMovements();
    }

    @Override
    public Movements getMovementsById(int id) throws SQLException {
        return movementsRepository.getMovementById(id);
    }

    @Override
    public Movements createMovements(Movements movement) throws SQLException {
        return movementsRepository.createMovement(movement);
    }

    @Override
    public Movements updateMovements(int id, Movements movement) throws SQLException {
        return movementsRepository.updateMovement(id, movement);
    }

    public Movements addStock(Movements movements) throws SQLException {
        if ("entrance".equals(movements.getType())) {
            Movements latestMovement = movementsRepository.findLatestMovementByIngredientTemplate(movements.getIdIngredientTemplate());
            double updatedQuantityRemaining = (latestMovement != null ? latestMovement.getQuantityRemaining() : 0) + movements.getQuantity();
            movements.setQuantityRemaining(updatedQuantityRemaining);
            return movementsRepository.createMovement(movements);
        } else {
            throw new IllegalArgumentException("Invalid movement type for adding stock");
        }
    }
    public Movements consumeStock(Movements movements) throws SQLException {
        if ("exit".equals(movements.getType())) {
            Movements latestMovement = movementsRepository.findLatestMovementByIngredientTemplate(movements.getIdIngredientTemplate());
            double updatedQuantityRemaining = (latestMovement != null ? latestMovement.getQuantityRemaining() : 0) - movements.getQuantity();
            if (updatedQuantityRemaining < 0) {
                throw new IllegalArgumentException("Not enough stock available");
            }
            movements.setQuantityRemaining(updatedQuantityRemaining);
            return movementsRepository.createMovement(movements);
        } else {
            throw new IllegalArgumentException("Invalid movement type for consuming stock");
        }
    }


}
