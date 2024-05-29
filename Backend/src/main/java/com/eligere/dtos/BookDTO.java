package com.eligere.dtos;

import java.time.LocalDate;

import com.eligere.validations.ISBN;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
	@NotNull(message = "ISBN cannot be null")
    @ISBN
    private String isbn;

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotNull(message = "Author cannot be null")
    @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
    private String author;

    @NotNull(message = "Publication date cannot be null")
    @PastOrPresent(message = "Publication date must be a past or present date")
    private LocalDate publicationDate;
}
