package com.example.restaurantmanagement.service;
import com.example.restaurantmanagement.model.Menus;

import java.sql.SQLException;
import java.util.List;

public interface MenusService {
    List<Menus> getAllMenus() throws SQLException;
    Menus getMenusById(int id) throws SQLException;
    Menus createMenus(Menus menus) throws SQLException;
    Menus updateMenus(int id, Menus menus) throws SQLException;

}
