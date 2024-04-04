package com.example.diningReview.controller;

import com.example.diningReview.model.Restaurant;
import com.example.diningReview.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    public RestaurantRepository restaurantRepository;

    RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return this.restaurantRepository.save(restaurant);
    }

    @GetMapping
    public Iterable<Restaurant> getAllRestaurants() {
        return this.restaurantRepository.findAll();
    }
}
