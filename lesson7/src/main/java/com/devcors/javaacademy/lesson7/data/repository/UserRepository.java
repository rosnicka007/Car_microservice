package com.devcors.javaacademy.lesson7.data.repository;

import com.devcors.javaacademy.lesson7.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findAllById(Long id);
}
