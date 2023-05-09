package com.company.screeny.controller;

import com.company.screeny.dto.MovieDTO;
import com.company.screeny.entity.Movie;
import com.company.screeny.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        var allMovies = service.getAllMovies();

        return ResponseEntity.ok(allMovies);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<Movie>> getPaginatedAllMovies(
            @RequestParam int page,
            @RequestParam int size
    ) {
        var paginatedAllMovies = service.getAllMoviesWithPagination(page, size);

        return ResponseEntity.ok(paginatedAllMovies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable String id) {
        var movie = service.getMovie(id);

        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody @Valid MovieDTO requestBody) {
        var movie = service.createMovie(requestBody);

        return new ResponseEntity(movie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(
            @PathVariable String id,
            @RequestBody @Valid MovieDTO requestBody
    ) {
        var movie = service.getMovie(id);
        var updatedMovie = service.updateMovie(movie, requestBody);

        return ResponseEntity.ok(updatedMovie);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Movie> partiallyUpdateMovie(@PathVariable String id, @RequestBody Map<String, Integer> requestBody) {
        var movie = service.partiallyUpdateMovie(id, requestBody);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovie(@PathVariable String id) {
        var movie = service.getMovie(id);
        service.deleteMovie(movie);
        var result = new HashMap<String, String>();
        result.put("detail", "Successfully deleted a movie with the given ID");

        return ResponseEntity.ok(result);
    }
}