package com.company.screeny.service;

import com.company.screeny.dto.MovieDTO;
import com.company.screeny.entity.Movie;
import com.company.screeny.exception.ObjectNotFoundException;
import com.company.screeny.repository.MovieRepository;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repository;

    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    public Page<Movie> getAllMoviesWithPagination(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public Movie getMovie(String id) {
        var optionalMovie = repository.findById(id);
        if (optionalMovie.isEmpty())
            throw new ObjectNotFoundException("Couldn't find a movie with the given ID");

        return optionalMovie.get();
    }

    public Movie createMovie(MovieDTO requestBody) {
        var movie = new Movie();
        return saveMovie(movie, requestBody);
    }

    public Movie updateMovie(Movie movie, MovieDTO requestBody) {
        return saveMovie(movie, requestBody);
    }

    public Movie partiallyUpdateMovie(@PathVariable String id, Map<String, Integer> requestBody) {
        var movie = getMovie(id);

        var movieDTO = new MovieDTO();
        movieDTO.setNumberInStock(requestBody.get("numberInStock"));

        var factory = Validation.buildDefaultValidatorFactory();
        var validator = factory.getValidator();
        var violations = validator.validateProperty(movieDTO, "numberInStock");
        var errors = new HashMap<String, String>();
        System.out.println(violations);
        if (!violations.isEmpty()) {
            violations.forEach(violation -> errors.put("detail", violation.getMessage()));
        }

        return movie;
    }

    public void deleteMovie(Movie movie) {
        repository.delete(movie);
    }

    private Movie saveMovie(Movie movie, MovieDTO requestBody) {
        movie.setTitle(requestBody.getTitle());
        movie.setRating(requestBody.getRating());
        movie.setDescription(requestBody.getDescription());
        movie.setNumberInStock(requestBody.getNumberInStock());
        movie.setGenreId(requestBody.getGenreId());

        return repository.save(movie);
    }
}
