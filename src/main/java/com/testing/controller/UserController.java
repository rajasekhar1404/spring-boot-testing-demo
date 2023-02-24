package com.testing.controller;

import com.testing.entity.User;
import com.testing.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id) throws Exception {
        User user = userService.findUserById(id);
        if(user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if(userService.addUser(user)) {
            return new ResponseEntity<>("User registered successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User already Registered, Please Login.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {

        if(userService.deleteUserById(id)) {
            return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}