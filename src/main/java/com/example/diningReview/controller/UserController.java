package com.example.diningReview.controller;


import com.example.diningReview.model.User;
import com.example.diningReview.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/search")
    public User searchUserByName(@RequestParam(value = "name", required = false) String name) {
        if(name== null) {
           return null;
        }
        Optional<User> foundUser= this.userRepository.findByName(name);
        return foundUser.orElse(null);
    }

    @PostMapping
    public User createNewUser(@RequestBody User user) {
        Optional<User> checkUser = this.userRepository.findByName(user.getName());
        if(checkUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User already Exists!");
        }
        return this.userRepository.save(user);
    }

    @PutMapping("/update")
    public  User updateUser(@RequestBody User user) {
        Optional<User> checkUser = this.userRepository.findByName(user.getName());
        if(checkUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not Found");
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
        if(user.isPeanutAllergies() != userToUpdate.isPeanutAllergies()) {
            userToUpdate.setPeanutAllergies(user.isPeanutAllergies());
        }

        userToUpdate.setPeanutAllergies(user.isPeanutAllergies());

        userToUpdate.setDiaryAllergies(user.isDiaryAllergies());

        userToUpdate.setEggAllergies(user.isEggAllergies());

        return this.userRepository.save(userToUpdate);
    }

    @DeleteMapping("/delete")
    public User deleteUser(@RequestParam(value = "name", required = false) String name) {
        Optional<User> user = this.userRepository.findByName(name);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not Found");
        }
        User userToDelete = user.get();
        this.userRepository.delete(userToDelete);
        return userToDelete;
    }

//    @DeleteMapping("/delete/{name}")
//    public User deleteUser(@PathVariable("name") String name) {
//
//    }

    public boolean checkChange(String string1, String string2) {
        return !string1.equals(string2);
    }

}
