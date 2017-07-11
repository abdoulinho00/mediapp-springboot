package com.aelbardai.web;

import com.aelbardai.article.domain.Article;
import com.aelbardai.article.service.ArticleService;
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
}
