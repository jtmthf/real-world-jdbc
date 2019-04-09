package com.example.realworldjdbc.user;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.realworldjdbc.security.DomainUserDetails;
import com.example.realworldjdbc.security.jwt.TokenProvider;
import com.example.realworldjdbc.user.dto.LoginDto;
import com.example.realworldjdbc.user.dto.RegisterDto;
import com.example.realworldjdbc.user.mapper.UserMapperImpl;
import com.example.realworldjdbc.user.mapper.UserMapperSupport;
import com.example.realworldjdbc.user.resources.UserResource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
    private TokenProvider tokenProvider = mock(TokenProvider.class);
    private UserRepository userRepository = mock(UserRepository.class);
    private UserService userService = new UserService(authenticationManager, tokenProvider,
        new UserMapperImpl(new UserMapperSupport(mock(PasswordEncoder.class))), userRepository);


    @Before
    public void setup() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenAnswer(invocation -> {
                UsernamePasswordAuthenticationToken token = invocation.getArgument(0);
                assertThat(token.getCredentials()).isEqualTo("password");
                TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken(
                    new DomainUserDetails(User.builder().username("test").email(token.getName()).build()), null);
                authenticationToken.setAuthenticated(true);
                return authenticationToken;
            });
        when(tokenProvider.createToken(any(Authentication.class))).thenReturn("token");

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testLogin() {
        LoginDto loginDto = LoginDto.builder().email("test@example.com").password("password").build();
        UserResource userResource = userService.login(loginDto);

        assertThat(userResource.getUser().getEmail()).isEqualTo("test@example.com");
        assertThat(userResource.getUser().getUsername()).isEqualTo("test");
        assertThat(userResource.getUser().getToken()).isEqualTo("token");

        assertThat(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()).isTrue();
    }

    @Test
    public void testRegister() {
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);

        RegisterDto registerDto = new RegisterDto("test", "test@example.com", "password");
        UserResource userResource = userService.register(registerDto);

        verify(userRepository).save(argumentCaptor.capture());

        assertThat(userResource.getUser().getEmail()).isEqualTo("test@example.com");
        assertThat(userResource.getUser().getUsername()).isEqualTo("test");
        assertThat(userResource.getUser().getToken()).isEqualTo("token");

        assertThat(argumentCaptor.getValue().getEmail()).isEqualTo("test@example.com");
        assertThat(argumentCaptor.getValue().getUsername()).isEqualTo("test");

        assertThat(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()).isTrue();
    }
}
