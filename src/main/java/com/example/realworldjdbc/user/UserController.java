package com.example.realworldjdbc.user;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.realworldjdbc.user.dto.LoginDto;
import com.example.realworldjdbc.user.dto.RegisterDto;
import com.example.realworldjdbc.user.dto.UpdateUserDto;
import com.example.realworldjdbc.user.resources.UserResource;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/login")
    public UserResource login(@Valid @RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResource register(@Valid @RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @GetMapping("/user")
    public ResponseEntity<UserResource> currentUser() {
        return ResponseEntity.ok(userService.currentUser());
    }

    @PutMapping("/user")
    public UserResource updateUser(@Valid @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(updateUserDto);
    }
}
