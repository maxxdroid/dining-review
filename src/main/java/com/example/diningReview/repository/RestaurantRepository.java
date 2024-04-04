package com.example.diningReview.repository;

import com.example.diningReview.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    int countByNameAndState(String name, String state);
}
