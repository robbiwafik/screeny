package com.company.screeny.validation.constraint;

import com.company.screeny.entity.Genre;
import com.company.screeny.repository.GenreRepository;
import com.company.screeny.validation.annotation.ValidGenreId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ReferencePKConstraintValidator implements ConstraintValidator<ValidGenreId, String> {
    @Autowired
    private GenreRepository repository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Genre> optionalGenre = repository.findById(id);
        return optionalGenre.isPresent();
    }
}
