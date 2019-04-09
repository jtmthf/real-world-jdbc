package com.example.realworldjdbc.user.dto;

import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class UserDto {

    String email;

    String token;

    String username;

    @Nullable
    String bio;

    @Nullable
    String image;
}
