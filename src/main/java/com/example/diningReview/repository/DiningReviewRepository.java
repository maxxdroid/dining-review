package com.example.diningReview.repository;


import com.example.diningReview.model.DiningReview;
import com.example.diningReview.model.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
    List<DiningReview> findByStatus(ReviewStatus status);
}
