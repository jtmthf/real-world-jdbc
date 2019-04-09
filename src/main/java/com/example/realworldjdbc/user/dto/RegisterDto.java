package com.example.realworldjdbc.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Value;

@Value
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonTypeName("user")
public class RegisterDto {

    String username;

    @Email
    String email;

    @Size(min = 8)
    String password;
}
