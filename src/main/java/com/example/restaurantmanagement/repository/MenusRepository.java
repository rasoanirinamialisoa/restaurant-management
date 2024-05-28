package com.example.restaurantmanagement.repository;
import com.example.restaurantmanagement.model.Menus;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public interface MenusRepository {
    List<Menus> getAllMenus() throws SQLException;
    Menus getMenusById(int id) throws SQLException;
    Menus createMenus(Menus menus) throws SQLException;
    Menus updateMenus(int id, Menus menus) throws SQLException;
    Menus deleteMenus(int id) throws SQLException;
}
