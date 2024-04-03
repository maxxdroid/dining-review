package com.example.diningReview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String city;

    private String state;

    private String zipcode;

    private boolean peanutAllergies;

    private boolean eggAllergies;

    private boolean diaryAllergies;
}
