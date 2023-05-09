package com.company.screeny.dto;

import com.company.screeny.validation.annotation.ValidGenreId;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    @NotNull(message = "Title can't be null")
    @NotEmpty(message = "Title can't be empty")
    private String title;

    @NotNull(message = "Rating can't be null")
    @DecimalMin(value = "1.00", message = "Rating can't be less than 1")
    @DecimalMax(value = "10.00", message = "Rating can't be greater than 10")
    private Double rating;

    @NotNull(message = "Stock can't be null")
    @Min(value = 0, message = "Stock can't be a negative number")
    private Integer numberInStock;

    @NotNull(message = "Description can't be null")
    private String description;

    @NotNull(message = "GenreId can't be null")
    @NotEmpty(message = "GenreId can't be empty")
    @ValidGenreId()
    private String genreId;
}
