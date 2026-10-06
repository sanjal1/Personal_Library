package com.sl.personal_library.dto;

import com.sl.personal_library.model.Review;

public class ReviewDTO {
    private String user;

    private int rating;

    private String comment;

    public ReviewDTO() {}

    public ReviewDTO(Review r) {
        this.user = r.getUser().getFirstName() + " " + r.getUser().getLastName();
        this.rating = r.getRating();
        this.comment = r.getComment();
    }

    public String getUser() {return user;}

    public void setUser(String user) {this.user = user;}

    public int getRating() {return rating;}

    public void setRating(int rating) {this.rating = rating;}

    public String getComment() {return comment;}

    public void setComment(String comment) {this.comment = comment;}

}
