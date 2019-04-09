package com.example.realworldjdbc.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import com.example.realworldjdbc.article.Article;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Builder
@Wither(AccessLevel.PACKAGE)
public class Comment {

    @Id
    @Nullable
    Long id;

    @Column("article_id")
    Long articleId;

    String body;

    @CreatedDate
    @Column("created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    LocalDateTime updatedAt;

    public static class CommentBuilder {

        public CommentBuilder article(Article article) {
            Assert.notNull(article.getId(), "Article id, must not be null");

            return articleId(article.getId());
        }
    }
}
