package com.example.diningReview.controller;

import com.example.diningReview.model.Restaurant;
import com.example.diningReview.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    public RestaurantRepository restaurantRepository;

    RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        int checkRestaurant = this.restaurantRepository.countByNameAndState(restaurant.getName(), restaurant.getState());
        if(checkRestaurant < 1) {
            return this.restaurantRepository.save(restaurant);
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Restaurant Already Exists");
        }
    }

    @GetMapping
    public Iterable<Restaurant> getAllRestaurants() {
        return this.restaurantRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public Restaurant deleteRestaurant(@PathVariable("id") Long id) {
        Optional<Restaurant> restaurantToDelete = this.restaurantRepository.findById(id);
        Restaurant restaurant;
        if (restaurantToDelete.isPresent()){
            restaurant = restaurantToDelete.get();
            this.restaurantRepository.deleteById(id);
            return restaurant;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not Found");
        }

    }

}
