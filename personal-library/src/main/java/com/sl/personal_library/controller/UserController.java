package com.sl.personal_library.controller;

import com.sl.personal_library.dto.*;
import com.sl.personal_library.model.User;
import com.sl.personal_library.service.BookService;
import com.sl.personal_library.service.ReviewService;
import com.sl.personal_library.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:5173"}, allowCredentials = "true")

public class UserController {
    @Autowired
    private UserService userService;
    private BookService bookService;
    private ReviewService reviewService;

    //not registered users
    @GetMapping("/number")
    public List<User> findAll() {return userService.findAllUsers();}

    //registration
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO dto) {
        try{
            User user = new User();
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());

            User saved = userService.register(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO dto, HttpSession session) {
        try{
            User user = userService.login(dto.getEmail(), dto.getPassword());
            session.setAttribute("user", user);
            return ResponseEntity.ok(new UserResponseDTO(user));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //my profile
    @GetMapping("/myProfile")
    public ResponseEntity<?> getMyProfile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user logged in");

        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    //log out
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Successfully logged out.");
    }

    //profile changes
    @PutMapping("/{id}")
    public ResponseEntity<?> changeProfile(@PathVariable Long id, @RequestBody UserUpdateProfileDTO dto, HttpSession session) {
        try{
            User user = userService.changeProfile(id, dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getProfilePicture());
            session.setAttribute("user", user);
            return ResponseEntity.ok(new UserResponseDTO(user));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //change profile pic
    @PutMapping("/{id}/profilePic")
    public ResponseEntity<?> changeProfilePicture(@PathVariable Long id, @RequestBody String profilePicture, HttpSession session) {
        try {
            String clearPic = profilePicture.replace("\"", "").trim();
            User user = userService.changeProfilePicture(id, profilePicture);
            session.setAttribute("user", user);
            return ResponseEntity.ok(new UserResponseDTO(user));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // change password
    @PutMapping("/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody UserChangePasswordDTO dto, HttpSession session) {
        try {
            if (!dto.getNewPassword().equals(dto.getConfirmNewPassword()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match.");

            User updated = userService.changePassword(id, dto.getOldPassword(), dto.getNewPassword());
            session.setAttribute("user", updated);
            return ResponseEntity.ok("Password changed successfully");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // personal statistics: books, reviews
    @GetMapping("/myProfile/stats")
    public ResponseEntity<?> getStats(HttpSession session) {
        if (session.getAttribute("user") == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in.");

        return ResponseEntity.ok(Map.of(
                "books", userService.getNumberOfBooks(),
                "reviews", userService.getNumberOfReviews()
        ));
    }


}
