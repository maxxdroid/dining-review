package com.example.diningReview.controller;

import com.example.diningReview.model.DiningReview;
import com.example.diningReview.model.Restaurant;
import com.example.diningReview.model.ReviewStatus;
import com.example.diningReview.repository.DiningReviewRepository;
import com.example.diningReview.repository.RestaurantRepository;
import com.example.diningReview.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("reviews")
public class DiningReviewController {
    RestaurantRepository restaurantRepository;
    DiningReviewRepository diningReviewRepository;
    UserRepository userRepository;

    DiningReviewController(RestaurantRepository restaurantRepository, DiningReviewRepository diningReviewRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.diningReviewRepository = diningReviewRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public DiningReview addReviews(@RequestBody DiningReview diningReview) {
        diningReview.setStatus(ReviewStatus.PENDING);
        return this.diningReviewRepository.save(diningReview);
    }

    @GetMapping
    public Iterable<DiningReview> getAllReviews() {
        return this.diningReviewRepository.findAll();
    }

}
