package com.aelbardai.web;

import com.aelbardai.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dev on 7/11/17.
 */
@Controller
@RequestMapping("/articles")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping({"","/"})
    public ModelAndView viewArticlesPage(){
        return new ModelAndView("articles/view");
    }

    @GetMapping("/{id}")
    public ModelAndView viewArticleDetails(@PathVariable("id") Long id){
        return new ModelAndView("articles/details" , "article" , articleService.getArticleById(id));
    }
}
