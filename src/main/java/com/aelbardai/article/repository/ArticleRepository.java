package com.aelbardai.article.repository;

import com.aelbardai.article.domain.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Article.class entity
 */
@Repository
public interface ArticleRepository extends CrudRepository<Article, Long>{
}
