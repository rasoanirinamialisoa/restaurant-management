package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Menus;
import com.example.restaurantmanagement.repository.MenusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MenusServiceImpl implements MenusService {

    @Autowired
    private final MenusRepository menusRepository;

    public MenusServiceImpl(MenusRepository menusRepository) {
        this.menusRepository = menusRepository;
    }

    @Override
    public List<Menus> getAllMenus() throws SQLException {
        return menusRepository.getAllMenus();
    }

    @Override
    public Menus getMenusById(int id) throws SQLException {
        return menusRepository.getMenusById(id);
    }

    @Override
    public Menus createMenus(Menus menus) throws SQLException {
        return menusRepository.createMenus(menus);
    }


    @Override
    public Menus updateMenus(int id, Menus menus) throws SQLException {
        return menusRepository.updateMenus(id, menus);
    }

}
