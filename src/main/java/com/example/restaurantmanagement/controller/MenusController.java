package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Menus;
import com.example.restaurantmanagement.service.MenusService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MenusController {

    private final MenusService menusService;

    public MenusController(MenusService menusService) {
        this.menusService = menusService;
    }

    @GetMapping("/menus")
    public List<Menus> getAllMenus() throws SQLException {
        return menusService.getAllMenus();
    }

    @GetMapping("/menus/{id}")
    public Menus getMenusById(@PathVariable int id) throws SQLException {
        return menusService.getMenusById(id);
    }

    @PostMapping("/menus")
    public Menus createMenus(@RequestBody Menus menus) throws SQLException {
        return menusService.createMenus(menus);
    }

    @PutMapping("/menus/{id}")
    public Menus updateMenus(@PathVariable int id, @RequestBody Menus menus) throws SQLException {
        return menusService.updateMenus(id, menus);
    }
    @DeleteMapping("/menus/{id}")
    public Menus deleteMenus(@PathVariable int id) throws SQLException  {
        return menusService.deleteMenus(id);
    }

}
