package com.devcors.javaacademy.lesson7.rest;

import com.devcors.javaacademy.lesson7.data.entity.User;
import com.devcors.javaacademy.lesson7.dto.UserDto;
import com.devcors.javaacademy.lesson7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // method for making a list of all users
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    // method for searching user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // method for creating a new user with Dto class
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
        User newUser = userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    // method for updating information about user
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto updatedUserDto) {
        Optional<User> updatedUser = userService.updateUser(id, updatedUserDto);

        if (updatedUser.isPresent()) {
            return ResponseEntity.ok(updatedUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User id " + id + " does not exist!");
        }
    }

    // method for deleting an user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);

        if (deleted) {
            return ResponseEntity.ok("User id " + id + " was deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User id " + id + " does not exist!");
        }
    }
}










