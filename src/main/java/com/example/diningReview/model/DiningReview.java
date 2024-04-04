package com.example.diningReview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Optional;

@Entity
@Data
public class DiningReview {

    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;

    private ReviewStatus status;

//    private Optional<Float> peanutScale;
//
//    private Optional<Float> eggScore;
//
//    private  Optional<Float> diaryScore;
//
//    private Optional<String> commentary;
}
