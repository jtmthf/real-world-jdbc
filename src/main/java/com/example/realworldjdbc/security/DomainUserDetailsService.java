package com.example.realworldjdbc.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.realworldjdbc.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        log.debug("Authenticating {}", email);

        return userRepository.findByEmail(email)
            .map(DomainUserDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " was not found"));
    }
}
