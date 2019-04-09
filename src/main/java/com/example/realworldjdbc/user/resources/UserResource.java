package com.example.realworldjdbc.user.resources;

import org.springframework.hateoas.ResourceSupport;

import com.example.realworldjdbc.user.dto.UserDto;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserResource extends ResourceSupport {

    UserDto user;

}
