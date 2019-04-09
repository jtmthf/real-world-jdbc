package com.example.realworldjdbc.user;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select * from users u where u.email = :email")
    Optional<User> findByEmail(String email);

    @Query("select * from users u where u.username = :username")
    Optional<User> findByUsername(String username);
}
