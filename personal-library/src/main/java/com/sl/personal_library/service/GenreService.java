package com.sl.personal_library.service;

import com.sl.personal_library.model.Genre;
import com.sl.personal_library.repository.GenreRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public Genre findOne(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent())
            return genre.get();
        return null;
    }

    public Genre findByName(String name) {return genreRepository.findByNameIgnoreCase(name);}

    public List<Genre> findFromAllGenres(String name) {return genreRepository.findByNameContainingIgnoreCase(name);}

    public List<Genre> findAll() {return genreRepository.findAll();}

    public Genre save(Genre genre) {return genreRepository.save(genre);}

    public void delete(Genre genre) {genreRepository.delete(genre);}

}
