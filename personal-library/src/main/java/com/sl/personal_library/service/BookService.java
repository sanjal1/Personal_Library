package com.sl.personal_library.service;

import com.sl.personal_library.model.Book;
import com.sl.personal_library.model.Review;
import com.sl.personal_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {return bookRepository.findAll();}

    public List<Book> findReview(List<Review> reviews) {return bookRepository.findByReviewsEquals(reviews);}

    public List<Book> findTitle(String title) {return bookRepository.findByTitleContainingIgnoreCase(title);}

    public List<Book> findByAuthorID(Long id) {return bookRepository.findByAuthors_Id(id);}

    public List<Book> findByAuthor(String firstName, String lastName) {return bookRepository.findByAuthors_FirstNameContainingIgnoreCaseOrAuthors_LastNameContainingIgnoreCase(firstName, lastName);}

    public List<Book> findGenre(String genre) {return bookRepository.findByGenres_NameContainingIgnoreCase(genre);}

    public long getNumberOfReadBooks() {return bookRepository.countAllByIdExists();}

    public Double getAverageRatingOfAllReadBooks() {return bookRepository.averageRating();}

    public Book save(Book book) {return bookRepository.save(book);}

    public void delete(Book book) {bookRepository.delete(book);}
}
