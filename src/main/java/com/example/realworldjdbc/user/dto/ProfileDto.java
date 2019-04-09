package com.example.realworldjdbc.user.dto;

import org.springframework.lang.Nullable;

import com.example.realworldjdbc.user.dto.ProfileDto.ProfileDtoBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProfileDto {

    String username;

    @Nullable
    String bio;

    @Nullable
    String image;

    boolean following;
}
