package com.company.screeny.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.company.screeny.entity.Genre;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {
}

