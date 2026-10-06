package com.sl.personal_library.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    //vise zanrova moze da pripada vise knjiga
    @ManyToMany(mappedBy = "genres", cascade = CascadeType.MERGE)
    private List<Book> books =  new ArrayList<>();

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public List<Book> getBooks() {return books;}

    public void setBooks(List<Book> books) {this.books = books;}


}
