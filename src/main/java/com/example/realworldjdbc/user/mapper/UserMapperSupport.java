package com.example.realworldjdbc.user.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.realworldjdbc.user.User;
import com.example.realworldjdbc.user.User.UserBuilder;
import com.example.realworldjdbc.user.dto.RegisterDto;
import com.example.realworldjdbc.user.dto.UpdateUserDto;
import com.example.realworldjdbc.user.dto.UserDto;
import com.example.realworldjdbc.user.dto.UserDto.UserDtoBuilder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapperSupport {

    private final PasswordEncoder passwordEncoder;

    UserDtoBuilder createUserDtoBuilder(@Context String token) {
        return UserDto.builder()
            .token(token);
    }

    UserBuilder createUserBuilder(@Context User user) {
        return user.toBuilder();
    }

    @AfterMapping
    protected void populatePassword(RegisterDto registerDto, @MappingTarget UserBuilder userBuilder) {
        userBuilder.password(passwordEncoder.encode(registerDto.getPassword()));
    }

    @AfterMapping
    protected void populatePassword(UpdateUserDto updateUserDto, @MappingTarget UserBuilder userBuilder) {
        if (updateUserDto.getPassword() != null) {
            userBuilder.password(passwordEncoder.encode(updateUserDto.getPassword()));
        }
    }
}
