package com.aelbardai.article.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Class represents an article to be published
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private static final long serialVersionUID = -6333546188980805846L;
    @Id
    @GeneratedValue
    private long id;
    private String title;
    @Column(columnDefinition = "text")
    private String content;
    private String tags;
    private String author;
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    //@Temporal(TemporalType.DATE)
    private Date writtenAt;
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    //@Temporal(TemporalType.DATE)
    private Date modifiedAt;
    private ArticleType type;
}
