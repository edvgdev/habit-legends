package com.habitlegends.habitlegends.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
