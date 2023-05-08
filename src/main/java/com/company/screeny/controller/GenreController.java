package com.company.screeny.controller;

import com.company.screeny.dto.GenreDTO;
import com.company.screeny.entity.Genre;
import com.company.screeny.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService service;

    @GetMapping
    public ResponseEntity<List<Genre>> genres() { // change the method name to getAllGenres
        return ResponseEntity.ok(service.getAllGenres());
    };

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<Genre>> getPaginatedAllGenres(
            @RequestParam int page,
            @RequestParam int size
    ) {
       return ResponseEntity.ok(service.getAllGenresWithPagination(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenre(@PathVariable String id) {
        return ResponseEntity.ok(service.getGenre(id));
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody @Valid GenreDTO requestBody) {
        return new ResponseEntity(
            service.createGenre(requestBody),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(
            @PathVariable String id,
            @RequestBody @Valid GenreDTO requestBody
    ) {
        return ResponseEntity.ok(service.updateGenre(id, requestBody));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGenre(@PathVariable String id) {
        service.deleteGenre(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
