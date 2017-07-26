package com.aelbardai.diet.service;

import com.aelbardai.diet.domain.CiqualMenuItem;
import com.aelbardai.diet.domain.MenuItem;
import com.aelbardai.diet.domain.MenuItemMapper;
import com.aelbardai.diet.repository.MenuItemRepository;
import com.aelbardai.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
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

    @Override
    public boolean importCiqualDB(String filename){
        File file;
        try {
            file = new ClassPathResource("static/assets/"+filename).getFile();
            ICsvBeanReader beanReader = null;
            try {
                beanReader = new CsvBeanReader(new FileReader(file), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

                // the header elements are used to map the values to the bean (names must match)
                final String[] header = beanReader.getHeader(true);
                final CellProcessor[] processors = getProcessors();

                CiqualMenuItem menuItem;
                while( (menuItem = beanReader.read(CiqualMenuItem.class, header, processors)) != null ) {
                    log.info("{} " , MenuItemMapper.convert(menuItem));
                    menuItemRepository.save(MenuItemMapper.convert(menuItem));
                }

            }
            finally {
                if( beanReader != null ) {
                    beanReader.close();
                }
            }

        } catch (IOException e) {
            log.info("problemem a 3chiri with file '{}'" , filename,e);
            log.debug("",e);
        }
        return true;
    }

    private CellProcessor[] getProcessors(){
        int fields = CiqualMenuItem.class.getDeclaredFields().length;
        log.info("number of fields : {}" , fields);
        CellProcessor[] cellProcessors = new CellProcessor[fields];
        for(CellProcessor cellProcessor : cellProcessors) {
            cellProcessor = new Optional();
        }
        return cellProcessors;
    }
}
