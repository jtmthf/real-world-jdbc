package com.example.realworldjdbc.user.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonTypeName("user")
public class LoginDto {

    @NotEmpty
    String email;

    @NotEmpty
    String password;
}
