package com.sl.personal_library.dto;

import com.sl.personal_library.model.Author;
import com.sl.personal_library.model.Book;

import java.util.List;

public class BookBasicsDTO {
    private Long id;
    private String title;
    private List<Author> authors;

    public BookBasicsDTO() {}

    public BookBasicsDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.authors = book.getAuthors();
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public List<Author> getAuthors() {return authors;}

    public void setAuthors(List<Author> authors) {this.authors = authors;}

}
