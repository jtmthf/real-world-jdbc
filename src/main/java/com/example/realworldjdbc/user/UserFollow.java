package com.example.realworldjdbc.user;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.Assert;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table("user_follow")
public class UserFollow {

    @Column("follower_id")
    Long followerId;

    @Column("followed_id")
    Long followedId;

    public static UserFollow create(User follower, User following) {
        Assert.notNull(follower.getId(), "follower id, must not be null");
        Assert.notNull(following.getId(), "follower id, must not be null");

        return new UserFollow(follower.getId(), following.getId());
    }
}
