package com.example.diningReview.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String city;

    private String state;

    private Float overallScore;

    private Float peanutScore;

    private Float diaryScore;

    private Float eggScore;
}
