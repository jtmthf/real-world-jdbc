package com.example.realworldjdbc.user.dto;

import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateUserDto {

    @Nullable
    String email;

    @Nullable
    String username;

    @Nullable
    String password;

    @Nullable
    String bio;

    @Nullable
    String image;
}
