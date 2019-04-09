package com.example.realworldjdbc.user;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Builder(toBuilder = true)
@Wither(AccessLevel.PACKAGE)
@Table("users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Nullable
    Long id;

    String email;

    String username;

    @Column("password_hash")
    String password;

    @Nullable
    String bio;

    @Nullable
    String image;
}
