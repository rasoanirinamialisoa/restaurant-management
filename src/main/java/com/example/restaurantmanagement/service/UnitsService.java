package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Units;
import java.sql.SQLException;
import java.util.List;

public interface UnitsService {
    List<Units> getAllUnits() throws SQLException;
    Units getUnitById(int id) throws SQLException;
    Units createUnit(Units unit) throws SQLException;
    Units updateUnit(int id, Units unit) throws SQLException;
}
