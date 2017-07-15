package com.aelbardai.diet.service;

import com.aelbardai.diet.domain.MenuItem;
import com.aelbardai.diet.repository.MenuItemRepository;
import com.aelbardai.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of MenuItemService
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuItemServiceImpl implements MenuItemService{

    private final MenuItemRepository menuItemRepository;

    @Override
    public MenuItem addMenuItem(MenuItem menuItem) {
        if(menuItem != null){
            return menuItemRepository.save(menuItem);
        }
        return null;
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findOne(id);
    }

    @Override
    public void deleteMenuItem(Long id) {
        menuItemRepository.delete(id);
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return (List<MenuItem>) menuItemRepository.findAll();
    }

    @Override
    public List<MenuItem> getMenuItemsByUser(User user) {
        return null;
    }
}
