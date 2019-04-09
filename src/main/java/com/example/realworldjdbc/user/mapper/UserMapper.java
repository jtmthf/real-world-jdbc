package com.example.realworldjdbc.user.mapper;

import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.realworldjdbc.user.User;
import com.example.realworldjdbc.user.dto.LoginDto;
import com.example.realworldjdbc.user.dto.RegisterDto;
import com.example.realworldjdbc.user.dto.UpdateUserDto;
import com.example.realworldjdbc.user.dto.UserDto;

@Mapper(uses = UserMapperSupport.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    @Mapping(target = "token", ignore = true)
    UserDto toDto(User user, @Context String token);

    LoginDto toLoginDto(RegisterDto user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bio", ignore = true)
    @Mapping(target = "image", ignore = true)
    User toEntity(RegisterDto user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    User updateUser(UpdateUserDto updateUserDto, @Context User user);
}
