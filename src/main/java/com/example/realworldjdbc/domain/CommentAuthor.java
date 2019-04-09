package com.example.realworldjdbc.domain;


import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.Assert;

import com.example.realworldjdbc.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table("comment_author")
public class CommentAuthor {

    @Column("author_id")
    Long authorId;

    @Column("comment_id")
    Long commentId;

    public static CommentAuthor create(User author, Comment comment) {
        Assert.notNull(author.getId(), "Author id, must not be null");
        Assert.notNull(comment.getId(), "Comment id, must not be null");

        return new CommentAuthor(author.getId(), comment.getId());
    }
}
