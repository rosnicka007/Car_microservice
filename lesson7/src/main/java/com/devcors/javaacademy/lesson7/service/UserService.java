package com.devcors.javaacademy.lesson7.service;

import com.devcors.javaacademy.lesson7.data.entity.User;
import com.devcors.javaacademy.lesson7.data.repository.UserRepository;
import com.devcors.javaacademy.lesson7.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // service method for making a list of all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // service method for searching a user by id
    public Optional<User> getUserById(Long id) {
        return userRepository.findAllById(id);
    }

    // service method for adding a new user
    public User addUser(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .address(userDto.getAddress())
                .role(userDto.getRole())
                .build();

        return userRepository.save(user);

    }

    // service method for updating information about user
    public Optional<User> updateUser(Long id, UserDto updatedUserDto) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setEmail(updatedUserDto.getEmail());
            existingUser.setFirstname(updatedUserDto.getFirstname());
            existingUser.setLastname(updatedUserDto.getLastname());
            existingUser.setAddress(updatedUserDto.getAddress());
            existingUser.setRole(updatedUserDto.getRole());

            User updatedUser = userRepository.save(existingUser);
            return Optional.of(updatedUser);
        } else {
            return Optional.empty();
        }
    }

    // method for deleting user by id
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}








