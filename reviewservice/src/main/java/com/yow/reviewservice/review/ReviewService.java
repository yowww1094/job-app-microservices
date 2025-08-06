package com.yow.reviewservice.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    Review getReviewById(Long reviewId);
    Review addReview(Long companyId, Review review);
    Review updateReview(Long reviewId, Review review);
    Boolean deleteReview(Long reviewId);
}
