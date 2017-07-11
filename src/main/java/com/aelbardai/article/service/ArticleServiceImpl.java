package com.aelbardai.article.service;

import com.aelbardai.article.domain.Article;
import com.aelbardai.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Implementation of article service
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    public Article saveArticle(Article article){
        if(article != null){
            if(article.getWrittenAt() ==null){
                article.setWrittenAt(new Date());
            }
            article.setModifiedAt(new Date());
            return articleRepository.save(article);
        }
        else{
            return article;
        }
    }
    public Article getArticleById(Long id){
        return articleRepository.findOne(id);
    }
    public List<Article> getAllArticles(){
        return (List<Article>) articleRepository.findAll();
    }
    public void deleteArticle(Long id){
        articleRepository.delete(id);
    }
}
