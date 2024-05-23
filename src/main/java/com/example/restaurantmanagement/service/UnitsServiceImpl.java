package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Units;
import com.example.restaurantmanagement.repository.UnitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UnitsServiceImpl implements UnitsService {

    @Autowired
    private final UnitsRepository unitsRepository;

    public UnitsServiceImpl(UnitsRepository unitsRepository) {
        this.unitsRepository = unitsRepository;
    }

    @Override
    public List<Units> getAllUnits() throws SQLException {
        return unitsRepository.getAllUnits();
    }

    @Override
    public Units getUnitById(int id) throws SQLException {
        return unitsRepository.getUnitsById(id);
    }

    @Override
    public Units createUnit(Units unit) throws SQLException {
        return unitsRepository.createUnits(unit);
    }

    @Override
    public Units updateUnit(int id, Units unit) throws SQLException {
        return unitsRepository.updateUnits(id, unit);
    }
}
