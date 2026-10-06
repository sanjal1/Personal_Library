package com.sl.personal_library.repository;

import com.sl.personal_library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Long> {
    //trazi po zanru (sve knjige koje si procitao)
    List<Genre> findByNameContainingIgnoreCase(String name);

    Genre findByNameIgnoreCase(String name);
}
