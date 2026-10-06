package com.sl.personal_library.repository;

import com.sl.personal_library.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    //trazi po odredjenoj oceni
    List<Review> findByRatingEquals(int rating);

    //recenzije korisnika
    List<Review> findByUserId(long userId);

    //gledamo da li smo vec dali raiting ovoj knjizi
    boolean existsByBookIdAndUserId(Long bookId, Long userId);
}
