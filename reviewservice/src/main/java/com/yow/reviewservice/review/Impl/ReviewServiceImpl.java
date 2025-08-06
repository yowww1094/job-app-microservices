package com.yow.reviewservice.review.Impl;

import com.yow.reviewservice.review.Review;
import com.yow.reviewservice.review.ReviewRepository;
import com.yow.reviewservice.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findReviewByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review getReviewById(Long reviewId){
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public Review addReview(Long companyId, Review review) {
        if (companyId != null){
            review.setCompanyId(companyId);
            Review newReview = reviewRepository.save(review);
            return newReview;
        } else {
            return null;
        }
    }

    @Override
    public Review updateReview(Long reviewId, Review review) {
        if (reviewId != null){
            Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
            if (reviewOptional.isPresent()) {
                Review updatedReview = reviewOptional.get();

                updatedReview.setTitle(review.getTitle());
                updatedReview.setDescription(review.getDescription());
                updatedReview.setRating(review.getRating());

                reviewRepository.save(updatedReview);
                return updatedReview;
            }
        }
        return null;
    }

    @Override
    public Boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            try {
                reviewRepository.delete(review);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
