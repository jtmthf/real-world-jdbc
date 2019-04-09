package com.example.realworldjdbc.user.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class RegisterDtoTest {

    @Autowired
    private JacksonTester<RegisterDto> json;

    @Test
    public void testDeserialize() throws Exception {
        assertThat(this.json.read("register-dto.json"))
            .isEqualTo(new RegisterDto("test", "test@example.com", "password"));
    }
}
