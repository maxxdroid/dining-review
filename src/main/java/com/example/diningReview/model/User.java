package com.example.diningReview.model;

import jakarta.persistence.Column;
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

    @Column(unique = true)
    private String name;

    private String city;

    private String state;

    private String zipcode;

    private boolean isPeanutAllergies;

    private boolean isEggAllergies;

    private boolean isDiaryAllergies;
}
