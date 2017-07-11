package com.aelbardai.article.service;

import com.aelbardai.article.domain.Article;

import java.util.List;

/**
 * Interface for article service
 */
public interface ArticleService {

    Article saveArticle(Article article);
    Article getArticleById(Long id);
    List<Article> getAllArticles();
    void deleteArticle(Long id);


}
