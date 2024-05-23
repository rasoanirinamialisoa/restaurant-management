package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Units;
import com.example.restaurantmanagement.service.UnitsService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UnitsController {

    private final UnitsService unitsService;

    public UnitsController(UnitsService unitsService) {
        this.unitsService = unitsService;
    }

    @GetMapping("/units")
    public List<Units> getAllUnits() throws SQLException {
        return unitsService.getAllUnits();
    }

    @GetMapping("/units/{id}")
    public Units getUnitsById(@PathVariable int id) throws SQLException {
        return unitsService.getUnitById(id);
    }

    @PostMapping("/units")
    public Units createUnits(@RequestBody Units units) throws SQLException {
        return unitsService.createUnit(units);
    }

    @PutMapping("/units/{id}")
    public Units updateUnits(@PathVariable int id, @RequestBody Units units) throws SQLException {
        return unitsService.updateUnit(id, units);
    }
}
