package com.company.screeny.service;

import com.company.screeny.dto.GenreDTO;
import com.company.screeny.dto.MovieDTO;
import com.company.screeny.entity.Genre;
import com.company.screeny.exception.ObjectNotFoundException;
import com.company.screeny.repository.GenreRepository;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository repository;

    public List<Genre> getAllGenres() {
        return repository.findAll();
    }

    public Page<Genre> getAllGenresWithPagination(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public Genre getGenre(String id) {
        var optionalGenre = repository.findById(id);
        if (optionalGenre.isEmpty())
            throw new ObjectNotFoundException("Couldn't find a genre with the given id");

        return optionalGenre.get();
    }

    public Genre createGenre(GenreDTO requestBody) {
        return saveGenre(
            new Genre(),
            requestBody
        );
    }

    public Genre updateGenre(String id, GenreDTO requestBody) {
        return saveGenre(
            getGenre(id),
            requestBody
        );
    }

    public void deleteGenre(String id) {
        repository.delete(getGenre(id));
    }

    private Genre saveGenre(Genre genre, GenreDTO requestBody) {
        genre.setName(requestBody.getName());

        return repository.save(genre);
    }
}
