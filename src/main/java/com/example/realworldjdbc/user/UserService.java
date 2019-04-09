package com.example.realworldjdbc.user;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.realworldjdbc.security.DomainUserDetails;
import com.example.realworldjdbc.security.jwt.TokenProvider;
import com.example.realworldjdbc.user.dto.LoginDto;
import com.example.realworldjdbc.user.dto.RegisterDto;
import com.example.realworldjdbc.user.dto.UpdateUserDto;
import com.example.realworldjdbc.user.mapper.UserMapper;
import com.example.realworldjdbc.user.resources.UserResource;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    UserResource login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return currentUser();
    }

    UserResource register(RegisterDto registerDto) {
        User user = userMapper.toEntity(registerDto);
        userRepository.save(user);

        return login(userMapper.toLoginDto(registerDto));
    }

    UserResource currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwt = tokenProvider.createToken(authentication);
        DomainUserDetails userDetails = (DomainUserDetails) authentication.getPrincipal();
        return new UserResource(userMapper.toDto(userDetails.getUser(), jwt));
    }

    UserResource updateUser(UpdateUserDto updateUserDto) {
        User user = ((DomainUserDetails) SecurityContextHolder.getContext().getAuthentication()).getUser();
        user = userMapper.updateUser(updateUserDto, user);
        userRepository.save(user);

        return currentUser();
    }
}
