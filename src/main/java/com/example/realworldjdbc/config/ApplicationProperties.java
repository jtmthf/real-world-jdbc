package com.example.realworldjdbc.config;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import javax.validation.Valid;

import org.hibernate.validator.constraints.time.DurationMax;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.validation.annotation.Validated;

import com.example.realworldjdbc.validator.NullOrBase64Min;
import com.example.realworldjdbc.validator.NullOrNotEmpty;
import com.example.realworldjdbc.validator.OneOf;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
@Getter
@Validated
public class ApplicationProperties {

    @Valid
    private final Security security = new Security();

    @Getter
    public static class Security {

        @Valid
        private final Authentication authentication = new Authentication();

        @Getter
        public static class Authentication {

            @Valid
            private final Jwt jwt = new Jwt();

            @Getter
            @Setter
            @OneOf({"secret", "base64Secret"})
            public static class Jwt {

                @NullOrNotEmpty
                private String secret;

                @NullOrBase64Min(256)
                private String base64Secret;

                @DurationMax(days = 7, message = "{jwt.duration.max}")
                @DurationUnit(ChronoUnit.SECONDS)
                private Duration tokenValidity = Duration.ofDays(1);
            }
        }
    }
}
