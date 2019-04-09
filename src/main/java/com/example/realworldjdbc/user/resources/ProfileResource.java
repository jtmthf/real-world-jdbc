package com.example.realworldjdbc.user.resources;

import org.springframework.hateoas.ResourceSupport;

import com.example.realworldjdbc.user.dto.ProfileDto;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class ProfileResource extends ResourceSupport {

    ProfileDto profile;
}
