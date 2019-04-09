package com.example.realworldjdbc.user.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.realworldjdbc.user.assembler.UserResourceAssembler;
import com.example.realworldjdbc.user.dto.UserDto;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class UserResourceTest {

    @Autowired
    private JacksonTester<UserResource> json;

    @Test
    public void testSerialize() throws Exception {
        UserResourceAssembler resourceAssembler = new UserResourceAssembler();
        UserDto userDto = UserDto.builder()
            .username("test")
            .email("test@example.com")
            .token("token")
            .bio("a test bio")
            .image("test.jpg")
            .build();
        UserResource userResource = resourceAssembler.toResource(userDto);

        assertThat(json.write(userResource)).isEqualToJson("expected.json", JSONCompareMode.NON_EXTENSIBLE);
    }
}
