package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Movements;
import com.example.restaurantmanagement.repository.MovementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    @Override
    public Movements updateQuantityRemaining(int id, Movements movements) throws SQLException {
        return null;
    }


}
