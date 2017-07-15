package com.aelbardai.web;

import com.aelbardai.diet.domain.MenuItem;
import com.aelbardai.diet.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dev on 7/14/17.
 */
@Controller
@RequestMapping("/diet")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DietController {

    private final MenuItemService menuItemService;

    @GetMapping("/maker")
    public ModelAndView menuMaker(){
        return new ModelAndView("diet/menu_maker" , "menuItems" , menuItemService.getAllMenuItems());
    }

    @GetMapping("/view/{id}")
    public MenuItem getMenuItem(@PathVariable("id") Long id){
        return menuItemService.getMenuItemById(id);
    }
}
