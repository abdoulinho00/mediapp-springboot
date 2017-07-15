package com.aelbardai.web.rest;

import com.aelbardai.diet.domain.MenuItem;
import com.aelbardai.diet.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dev on 7/15/17.
 */
@RestController
@RequestMapping("/api/diet")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DietRestController {

    private final MenuItemService menuItemService;

    @GetMapping("/view/{id}")
    public MenuItem getMenuItem(@PathVariable("id") Long id){
        return menuItemService.getMenuItemById(id);
    }
}
