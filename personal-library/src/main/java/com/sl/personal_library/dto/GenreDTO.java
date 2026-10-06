package com.sl.personal_library.dto;

import com.sl.personal_library.model.Genre;

public class GenreDTO {
    private Long id;
    private String name;
    private int numberOfBooks;

    public GenreDTO() {}

    public GenreDTO(Genre g) {
        this.id = g.getId();
        this.name = g.getName();
        this.numberOfBooks = g.getBooks().size();
    }
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getNumberOfBooks() {return numberOfBooks;}

    public void setNumberOfBooks(int numberOfBooks) {this.numberOfBooks = numberOfBooks;}

}
