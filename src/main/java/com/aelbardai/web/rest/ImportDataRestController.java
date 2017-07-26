package com.aelbardai.web.rest;

import com.aelbardai.diet.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for importing ciqual database
 */
@RestController
@RequestMapping("/api/import")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImportDataRestController {

    private final MenuItemService menuItemService;

    @GetMapping("ciqual")
    public boolean importCiqualToDatabse(){
        boolean succeed =true;
        menuItemService.importCiqualDB("Table_Ciqual_2016.csv");
        return succeed;
    }
}
