/*
package com.devcors.javaacademy.lesson6.data.repository.impl;

import com.devcors.javaacademy.lesson6.data.entity.User;
import com.devcors.javaacademy.lesson6.data.entity.enums.UserRole;
import com.devcors.javaacademy.lesson6.data.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private List<User> users = List.of(
            User.builder()
                    .id(1L)
                    .email("admin@email.com")
                    .firstname("Firstname")
                    .lastname("Lastname")
                    .role(UserRole.ADMIN)
                    .address("Downing street 10")
                    .build(),
            User.builder()
                    .id(2L)
                    .email("user@email.com")
                    .firstname("Firstname")
                    .lastname("Lastname")
                    .role(UserRole.USER)
                    .address("Baker street 10")
                    .build()
    );

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream().filter(car -> id.equals(car.getId())).findFirst();
    }

}
*/