package com.sl.personal_library.service;

import com.sl.personal_library.model.User;
import com.sl.personal_library.repository.BookRepository;
import com.sl.personal_library.repository.ReviewRepository;
import com.sl.personal_library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private BookRepository bookRepository;
    private ReviewRepository reviewRepository;

    //register, log in
    public User register(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        user.setPassword(PasswordService.hashPassword(user.getPassword()));

        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        if(!PasswordService.checkPassword(password,user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return user;
    }

    //change profile, change password,change profile picture
    public User changeProfile(Long id, String firstName, String lastName, String profilePicture) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setProfilePicture(profilePicture);

        return userRepository.save(user);
    }

    public User changePassword(String email, String newPassword, String oldPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        if(!PasswordService.checkPassword(oldPassword,user.getPassword())) {
            throw new RuntimeException("Old password is incorrect.");
        }
        user.setPassword(PasswordService.hashPassword(newPassword));
        return userRepository.save(user);
    }

    public User changeProfilePicture(Long id, String profilePicture) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        user.setProfilePicture(profilePicture);
        return userRepository.save(user);
    }

    //nuber of users
    public long getNumberOfUsers(){
        return userRepository.count();
    }

    //nuber of read books
    public long getNumberOfBooks(){
        return  bookRepository.count();
    }

    //number of reviews
    public long getNumberOfReviews(){
        return  reviewRepository.count();
    }

}
