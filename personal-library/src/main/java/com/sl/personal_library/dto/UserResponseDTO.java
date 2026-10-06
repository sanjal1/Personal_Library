package com.sl.personal_library.dto;

import com.sl.personal_library.model.User;

public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String profilePicture;

    public UserResponseDTO(){}

    public UserResponseDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.profilePicture = user.getProfilePicture() != null ? user.getProfilePicture().replace("\"", "").trim() : null;
    }
    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getProfilePicture() {return profilePicture;}

    public void setProfilePicture(String profilePicture) {this.profilePicture = profilePicture;}

}
