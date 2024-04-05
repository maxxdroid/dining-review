package com.example.diningReview.controller;

import com.example.diningReview.model.DiningReview;
import com.example.diningReview.model.Restaurant;
import com.example.diningReview.model.ReviewStatus;
import com.example.diningReview.repository.DiningReviewRepository;
import com.example.diningReview.repository.RestaurantRepository;
import com.example.diningReview.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        Optional<Restaurant> resturantToUpdateOptional = this.restaurantRepository.findById(diningReview.getRestaurantId());
        if (resturantToUpdateOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant does not exist");
        }
        Restaurant restaurantToUpdate = resturantToUpdateOptional.get();
        Restaurant updatedRestaurant = updateRestaurant(restaurantToUpdate, diningReview);
        this.restaurantRepository.save(updatedRestaurant);
        return this.diningReviewRepository.save(diningReview);
    }

    public Restaurant updateRestaurant( Restaurant restaurantToUpdate, DiningReview diningReview ) {
        float oldOverallScore = restaurantToUpdate.getOverallScore();
        if (oldOverallScore > 0) {
            float newOverallScore = (diningReview.getDiaryScore() + diningReview.getEggScore() + diningReview.getPeanutScore())/3;
            newOverallScore = oldOverallScore + oldOverallScore;
            restaurantToUpdate.setOverallScore(newOverallScore);
        } else {
            float newOverallScore = (diningReview.getDiaryScore() + diningReview.getEggScore() + diningReview.getPeanutScore())/3;
            restaurantToUpdate.setOverallScore(newOverallScore);
        }
        return restaurantToUpdate;
    }

    @GetMapping
    public Iterable<DiningReview> getAllReviews() {
        return this.diningReviewRepository.findAll();
    }

}
