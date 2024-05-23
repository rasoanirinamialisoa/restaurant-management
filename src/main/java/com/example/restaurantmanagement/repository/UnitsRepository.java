package com.example.restaurantmanagement.repository;


import com.example.restaurantmanagement.model.Units;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public interface UnitsRepository {
    List<Units> getAllUnits() throws SQLException;
    Units getUnitsById(int id) throws SQLException;
    Units createUnits(Units units) throws SQLException;
    Units updateUnits(int id, Units Units) throws SQLException;
}
