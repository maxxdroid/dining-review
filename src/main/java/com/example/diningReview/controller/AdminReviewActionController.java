package com.example.diningReview.controller;

import com.example.diningReview.model.DiningReview;
import com.example.diningReview.model.ReviewStatus;
import com.example.diningReview.repository.DiningReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminReviewActionController {
    DiningReviewRepository diningReviewRepository;

    public AdminReviewActionController(DiningReviewRepository diningReviewRepository) {
        this.diningReviewRepository = diningReviewRepository;
    }

    @GetMapping
    public List<DiningReview> getPendingReviews() {
        return this.diningReviewRepository.findByStatus(ReviewStatus.PENDING);
    }

    @PostMapping("/review/{id}")
    public DiningReview changeDiningReviewStatus(@PathVariable("id") Long id, @RequestParam Boolean acceptStatus) {
        Optional<DiningReview> reviewToAcceptOptional = this.diningReviewRepository.findById(id);
        if (reviewToAcceptOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found.");
        }
        DiningReview reviewToAccept = reviewToAcceptOptional.get();
        if (acceptStatus) {
            reviewToAccept.setStatus(ReviewStatus.ACCEPTED);
        } else {
            reviewToAccept.setStatus(ReviewStatus.REJECTED);
        }
        return this.diningReviewRepository.save(reviewToAccept);
    }
}
