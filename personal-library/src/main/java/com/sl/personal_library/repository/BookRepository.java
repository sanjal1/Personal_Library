package com.sl.personal_library.repository;

import com.sl.personal_library.model.Author;
import com.sl.personal_library.model.Book;
import com.sl.personal_library.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {
    //filtriraj po recenziji
    List<Book> findByReviewsEquals(List<Review> reviews);

    //trazimo knjigu po naslovu
    List<Book> findByTitleContainingIgnoreCase(String title);

    //trazimo knjigu po id od autora
    List<Book> findByAuthors_Id(Long authors_id);

    //trazi po imenu i/ili prezimenu autora
    List<Book> findByAuthors_FirstNameContainingIgnoreCaseOrAuthors_LastNameContainingIgnoreCase(String firstName, String lastName);

    //trazi knjigu po zanru
    List<Book> findByGenres_NameContainingIgnoreCase(String genre);

    //ukupan br. knjiga
    long countAllByIdExists();

    //prosecna ocena svih ocenjenih knjiga
    @Query("SELECT AVG(r.rating) FROM Book b JOIN b.reviews r")
    Double averageRating();

}
