package com.example.realworldjdbc.article;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.Assert;

import com.example.realworldjdbc.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table("article_favorite")
public class ArticleFavorite {

    @Column("user_id")
    Long userId;

    @Column("article_id")
    Long articleId;

    public static ArticleFavorite create(User user, Article article) {
        Assert.notNull(user.getId(), "User id, must not be null");
        Assert.notNull(article.getId(), "Article id, must not be null");

        return new ArticleFavorite(user.getId(), article.getId());
    }
}
