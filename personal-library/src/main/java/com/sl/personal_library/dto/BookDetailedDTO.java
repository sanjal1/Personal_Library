package com.sl.personal_library.dto;

import com.sl.personal_library.model.Author;
import com.sl.personal_library.model.Book;
import com.sl.personal_library.model.Genre;

import java.util.List;

public class BookDetailedDTO {
    private Long id;

    private String title;

    private List<Author> authors;

    private List<Genre>  genres;

    //treba mi : prosecna ocena knjige i broj procitanih knjiga

    private int numberOfBooks;

    private double averageRating;

    public BookDetailedDTO() {}

    public BookDetailedDTO(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.authors = book.getAuthors();
        this.genres = book.getGenres();
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public List<Author> getAuthors() {return authors;}

    public void setAuthors(List<Author> authors) {this.authors = authors;}

    public List<Genre> getGenres() {return genres;}

    public void setGenres(List<Genre> genres) {this.genres = genres;}

    public int getNumberOfBooks() {return numberOfBooks;}

    public void setNumberOfBooks(int numberOfBooks) {this.numberOfBooks = numberOfBooks;}

    public double getAverageRating() {return averageRating;}

    public void setAverageRating(double averageRating) {this.averageRating = averageRating;}

}
