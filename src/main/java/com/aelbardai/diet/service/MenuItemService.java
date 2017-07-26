package com.aelbardai.diet.service;

import com.aelbardai.diet.domain.MenuItem;
import com.aelbardai.user.domain.User;

import java.util.List;

/**
 * Service for MenuItem
 */
public interface MenuItemService {

    MenuItem addMenuItem(MenuItem menuItem);
    MenuItem getMenuItemById(Long id);
    void deleteMenuItem(Long id);
    List<MenuItem> getAllMenuItems();
    List<MenuItem> getMenuItemsByUser(User user);
    boolean importCiqualDB(String filename);
}
