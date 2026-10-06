package com.sl.personal_library.service;

import com.sl.personal_library.model.Author;
import com.sl.personal_library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAuthor(String firstName, String lastName) {return authorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(firstName, lastName);}

    public Author save(Author author) {return authorRepository.save(author);}

    public void delete(Author author) {authorRepository.delete(author);}
}
