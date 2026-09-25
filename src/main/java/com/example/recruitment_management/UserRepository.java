package com.example.recruitment_management;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // DB検索
    Optional<User> findByUsername(String username);
}