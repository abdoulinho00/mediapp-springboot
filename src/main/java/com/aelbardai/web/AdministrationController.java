package com.aelbardai.web;

import com.aelbardai.article.domain.Article;
import com.aelbardai.article.service.ArticleService;
import com.aelbardai.diet.domain.MenuItem;
import com.aelbardai.diet.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for all administration operations
 */
@Controller
@RequestMapping("/administration")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdministrationController {

    private final ArticleService articleService;
    private final MenuItemService menuItemService;

    @GetMapping({"","/"})
    public ModelAndView adminMainPage(){
        return new ModelAndView("admin/index");
    }

    /*
        Controller methods for articles
     */
    @GetMapping("/articles/edit")
    public ModelAndView addEditArticleForm(@RequestParam(value="id" , required=false) Long id){
        Article article =null;
        if(id != null){
            article = articleService.getArticleById(id);
        }
        return new ModelAndView("articles/add" , "article" , article==null?new Article():article);
    }

    @PostMapping("/articles/edit")
    public ModelAndView addEditArticle(Article article){
        articleService.saveArticle(article);
        return new ModelAndView("redirect:/administration");
    }

    @GetMapping({"/articles/list"})
    public ModelAndView viewAllArticles(){
        return new ModelAndView("articles/list" , "articles" , articleService.getAllArticles());
    }

    /*
        Controller methods for patients
     */

    /*
        Controller methods for menu items
     */
    @GetMapping("/diet/add")
    public ModelAndView addMenuItemForm(@RequestParam(value="id" ,required = false) Long id){
        MenuItem menuItem =null;
        if(id !=null){
            menuItem = menuItemService.getMenuItemById(id);
            if(menuItem == null){
                return new ModelAndView("error/404" , "message" , "No menu found");
            }
            else{
                return new ModelAndView("diet/add" , "menuItem" , menuItem);
            }
        }
        else{
            return new ModelAndView("diet/add" , "menuItem" , new MenuItem());
        }
    }

    @PostMapping("/diet/add")
    public ModelAndView addMenuItem(MenuItem menuItem){
        menuItemService.addMenuItem(menuItem);
        return new ModelAndView("redirect:/administration/diet/list");
    }

    @GetMapping("/diet/list")
    public ModelAndView listMenuItems(){
        return new ModelAndView("diet/list" , "menuItems" , menuItemService.getAllMenuItems());
    }
}
