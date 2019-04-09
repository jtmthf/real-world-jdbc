package com.example.realworldjdbc.user.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.example.realworldjdbc.user.UserController;
import com.example.realworldjdbc.user.dto.UserDto;
import com.example.realworldjdbc.user.resources.UserResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class UserResourceAssembler extends ResourceAssemblerSupport<UserDto, UserResource> {

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(UserDto entity) {
        UserResource resource = new UserResource(entity);
        resource.add(linkTo(methodOn(UserController.class).currentUser()).withSelfRel());
        return resource;
    }
}
