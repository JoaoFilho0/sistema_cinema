package com.system.movietheater.infrastructure.validations.horary;

import com.system.movietheater.application.dto.horary.RegisterHoraryDto;
import com.system.movietheater.application.dto.movietheater.ListingMovieTheaterDto;
import com.system.movietheater.domain.model.Horary;
import com.system.movietheater.domain.model.MovieTheater;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationMovieTheaterHoraryTest {

    @Test
    void checkHorarySyntaxIsValid() {
        Horary horary = new Horary(
                new RegisterHoraryDto(
                        "Segunda-feira",
                        "10:00-18:00",
                        new ListingMovieTheaterDto(new MovieTheater()
                        )
                )
        );

        ValidationMovieTheaterHorary validate = new ValidationMovieTheaterHorary();

        Assertions.assertDoesNotThrow(() -> validate.validate(horary));
    }
}