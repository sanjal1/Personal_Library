package com.sl.personal_library.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    //vise autora moze da napise jednu knjigu
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    private String picture;

    private String color;

    public Book() {}

    public Book(Long id, String title, List<Author> authors, String picture, String color) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.picture = picture;
        this.color = color;
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.MERGE)
    private List<Review> reviews = new ArrayList<>();

    //jedna knjiga moze da ima vise zanrova
    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public List<Author> getAuthors() {return authors;}

    public void setAuthors(List<Author> authors) {this.authors = authors;}

    public String getPictureUrl() {return picture;}

    public void setPictureUrl(String pictureUrl) {this.picture = picture;}

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    public List<Review> getReviews() {return reviews;}

    public void setReviews(List<Review> reviews) {this.reviews = reviews;}

    public List<Genre> getGenres() {return genres;}

    public void setGenres(List<Genre> genres) {this.genres = genres;}


}
