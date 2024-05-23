package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Movements;
import com.example.restaurantmanagement.service.MovementsService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovementsController {

    private final MovementsService movementsService;

    public MovementsController(MovementsService movementsService) {
        this.movementsService = movementsService;
    }

    @GetMapping("/movements")
    public List<Movements> getAllMovements() throws SQLException {
        return movementsService.getAllMovements();
    }

    @GetMapping("/movements/{id}")
    public Movements getMovementsById(@PathVariable int id) throws SQLException {
        return movementsService.getMovementsById(id);
    }

    @PostMapping("/movements")
    public Movements createMovements(@RequestBody Movements movements) throws SQLException {
        return movementsService.createMovements(movements);
    }

    @PutMapping("/movements/{id}")
    public Movements updateMovements(@PathVariable int id, @RequestBody Movements movements) throws SQLException {
        return movementsService.updateMovements(id, movements);
    }
}
