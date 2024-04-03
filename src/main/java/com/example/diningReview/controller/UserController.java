package com.example.diningReview.controller;


import com.example.diningReview.model.User;
import com.example.diningReview.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    public final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return  this.userRepository.findAll();
    }

    @GetMapping("/users/search")
    public User searchUserByName(@RequestParam(value = "name", required = false) String name) {
        if(name== null) {
           return null;
        }
        Optional<User> foundUser= this.userRepository.findByName(name);
        return foundUser.orElse(null);
    }

    @PostMapping
    public User createNewUser(@RequestBody User user) {
        Optional<User> checkUser = this.userRepository.findById(user.getId());
        if(checkUser.isEmpty()) {
            return null;
        }
        return this.userRepository.save(user);
    }

    @PostMapping
    public  User updateUser(@RequestBody User user) {
        Optional<User> checkUser = this.userRepository.findByName(user.getName());
        if(checkUser.isEmpty()) {
            return null;
        }
        User userToUpdate = checkUser.get();
        if (checkChange(userToUpdate.getCity(), user.getCity()) && userToUpdate.getCity()!=null) {
            userToUpdate.setCity(user.getCity());
        }
        if (checkChange(userToUpdate.getState(), user.getState()) && userToUpdate.getState()!=null) {
            userToUpdate.setState(user.getState());
        }
        if (checkChange(userToUpdate.getZipcode(), user.getZipcode()) && userToUpdate.getZipcode()!=null) {
            userToUpdate.setZipcode(user.getZipcode());
        }
        return this.userRepository.save(userToUpdate);
    }

    public boolean checkChange(String string1, String string2) {
        return !string1.equals(string2);
    }

}
