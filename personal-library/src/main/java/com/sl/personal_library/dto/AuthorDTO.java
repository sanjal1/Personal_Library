package com.sl.personal_library.dto;

import com.sl.personal_library.model.Author;
import com.sl.personal_library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    private String firstName;

    private String lastName;

    private List<Book> books = new ArrayList<>();

    public AuthorDTO() {}

    public AuthorDTO(Author author) {
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
        this.books = author.getBooks();
    }

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public List<Book> getBooks() {return books;}

    public void setBooks(List<Book> books) {this.books = books;}

}
