package com.example.realworldjdbc.user;

import java.util.Collections;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import com.example.realworldjdbc.user.UserTest.Initializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@DataJdbcTest
@ContextConfiguration(initializers = Initializer.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@Transactional
public class UserTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer(
        "postgres:10.7-alpine").withTmpFs(
        Collections.singletonMap("/var/lib/postgresql/data", "rw")
    );

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveNewUser() {
        User user = User.builder()
            .email("test@example.com")
            .username("test")
            .password("hash")
            .build();

        user = userRepository.save(user);

        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void testUpdateUser() {
        User user = User.builder()
            .email("test@example.com")
            .username("test")
            .password("hash")
            .build();

        user = userRepository.save(user);
        Long id = user.getId();

        user = user.withBio("I am a test user").withImage("http://example.com/test.jpg");

        user = userRepository.save(user);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getBio()).isEqualTo("I am a test user");
        assertThat(user.getImage()).isEqualTo("http://example.com/test.jpg");
    }

    @Test
    public void testEmailRequired() {
        User user = User.builder()
            .username("test")
            .password("hash")
            .build();

        assertThatThrownBy(() -> userRepository.save(user))
            .hasCauseInstanceOf(DataIntegrityViolationException.class)
            .hasStackTraceContaining("\"email\" violates not-null constraint");
    }

    @Test
    public void testUsernameRequired() {
        User user = User.builder()
            .email("test@example.com")
            .password("hash")
            .build();

        assertThatThrownBy(() -> userRepository.save(user))
            .hasCauseInstanceOf(DataIntegrityViolationException.class)
            .hasStackTraceContaining("\"username\" violates not-null constraint");
    }

    @Test
    public void testPasswordRequired() {
        User user = User.builder()
            .username("test")
            .email("test@example.com")
            .build();

        assertThatThrownBy(() -> userRepository.save(user))
            .hasCauseInstanceOf(DataIntegrityViolationException.class)
            .hasStackTraceContaining("\"password_hash\" violates not-null constraint");
    }

    @Test
    public void testUsernameUnique() {
        User user1 = User.builder()
            .username("test")
            .email("test1@example.com")
            .password("hash")
            .build();

        userRepository.save(user1);

        User user2 = User.builder()
            .username("test")
            .email("test2@example.com")
            .password("hash")
            .build();

        assertThatThrownBy(() -> userRepository.save(user2))
            .hasCauseInstanceOf(DuplicateKeyException.class)
            .hasStackTraceContaining("(username)=(test) already exists");
    }

    @Test
    public void testEmailUnique() {
        User user1 = User.builder()
            .username("test1")
            .email("test@example.com")
            .password("hash")
            .build();

        userRepository.save(user1);

        User user2 = User.builder()
            .username("test2")
            .email("test@example.com")
            .password("hash")
            .build();

        assertThatThrownBy(() -> userRepository.save(user2))
            .hasCauseInstanceOf(DuplicateKeyException.class)
            .hasStackTraceContaining("(email)=(test@example.com)");
    }

    @Test
    public void testFindByEmail() {
        User user = User.builder()
            .email("test@example.com")
            .username("test")
            .password("hash")
            .build();

        userRepository.save(user);

        assertThat(userRepository.findByEmail("test@example.com")).isPresent();
    }

    @Test
    public void testFindByUsername() {
        User user = User.builder()
            .email("test@example.com")
            .username("test")
            .password("hash")
            .build();

        userRepository.save(user);

        assertThat(userRepository.findByUsername("test")).isPresent();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(applicationContext.getEnvironment());
        }
    }
}
