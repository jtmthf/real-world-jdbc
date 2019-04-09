package com.example.realworldjdbc.article;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import com.example.realworldjdbc.domain.Tag;
import com.example.realworldjdbc.user.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.experimental.PackagePrivate;
import lombok.experimental.Wither;

@Value
@Wither(AccessLevel.PACKAGE)
@Builder
public class Article {

    @Id
    @Nullable
    Long id;

    String slug;

    String title;

    String description;

    String body;

    @CreatedDate
    @Column("created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    LocalDateTime updatedAt;

    @Singular
    Set<Tag> tags;

    @Column("author_id")
    @PackagePrivate
    Long authorId;

    public static final class ArticleBuilder {

        public ArticleBuilder author(User author) {
            Assert.notNull(author.getId(), "Author id, must not be null");

            return authorId(author.getId());
        }

        private ArticleBuilder authorId(Long authorId) {
            this.authorId = authorId;
            return this;
        }
    }
}
