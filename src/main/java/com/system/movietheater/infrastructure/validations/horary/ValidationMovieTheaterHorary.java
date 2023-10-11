package com.system.movietheater.infrastructure.validations.horary;

import com.system.movietheater.domain.model.Horary;
import com.system.movietheater.infrastructure.exceptions.HoraryInvalidException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ValidationMovieTheaterHorary implements ValidationHorary {

    public void validate(Horary data) {
        try {
            String[] horary = data.getHorary().split("-");
            List<Integer> horaryInit = Arrays.stream(horary[0].split(":")).map(Integer::parseInt).toList();
            List<Integer> horaryEnd = Arrays.stream(horary[1].split(":")).map(Integer::parseInt).toList();

            if ((horaryInit.get(0) < 0 || horaryInit.get(0) > 23) || (horaryInit.get(1) < 0 || horaryInit.get(1) > 60)) {
                throw new HoraryInvalidException("Horary is invalid");
            }
            if ((horaryEnd.get(0) < 0 || horaryEnd.get(0) > 23) || (horaryEnd.get(1) < 0 || horaryEnd.get(1) > 60)) {
                throw new HoraryInvalidException("Horary is invalid");
            }
        } catch (NumberFormatException exception) {
            throw new HoraryInvalidException("Horary is invalid");
        }
    }
}
