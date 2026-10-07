package com.sl.personal_library.service;

import com.sl.personal_library.model.Review;
import com.sl.personal_library.repository.ReviewRepository;
import com.sl.personal_library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findByRating(int rating) {return reviewRepository.findByRatingEquals(rating);}

    public List<Review> findByUserId(long userId) {return reviewRepository.findByUserId(userId);}

    public Review save(Review review) {
        if(review.getRating() < 1 || review.getRating() > 5) {
            throw new RuntimeException("Review rating must be between 1 and 5!");
        }
        if(reviewRepository.existsByBookIdAndUserId(review.getUser().getId(), review.getBook().getId())) {
            throw new RuntimeException("Review already exists!");
        }

        return reviewRepository.save(review);
    }

    public void delete(Review review) {reviewRepository.delete(review);}

    public List<Review> findAll() {return reviewRepository.findAll();}

    public Review change(Long reviewId, int rating, String comment) {
        if(rating < 1 || rating > 5) {
            throw new RuntimeException("Review rating must be between 1 and 5!");
        }
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review.getUser() == null) {
            throw new RuntimeException("Review User not found!");
        }
        if(review.getBook() == null) {
            throw new RuntimeException("Review Book not found!");
        }
        review.setRating(rating);
        review.setComment(comment);

        return reviewRepository.save(review);
    }

}
