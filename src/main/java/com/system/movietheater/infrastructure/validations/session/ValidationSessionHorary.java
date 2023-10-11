package com.system.movietheater.infrastructure.validations.session;

import com.system.movietheater.domain.model.Session;
import com.system.movietheater.infrastructure.exceptions.HoraryInvalidException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ValidationSessionHorary {

    public void validate(Session data) {
        try {
            String[] sessionDuration = data.getHorary().split("-");
            List<Integer> sessionInit = Arrays.stream(sessionDuration[0].split(":")).map(Integer::parseInt).toList();
            List<Integer> sessionEnd = Arrays.stream(sessionDuration[1].split(":")).map(Integer::parseInt).toList();

            if ((sessionInit.get(0) < 0 || sessionInit.get(0) > 23) || (sessionInit.get(1) < 0 || sessionInit.get(1) > 60)) {
                throw new HoraryInvalidException("Horary is invalid");
            }
            if ((sessionEnd.get(0) < 0 || sessionEnd.get(0) > 23) || (sessionEnd.get(1) < 0 || sessionEnd.get(1) > 60)) {
                throw new HoraryInvalidException("Horary is invalid");
            }
        } catch (NumberFormatException exception) {
            throw new HoraryInvalidException("Horary is invalid");
        }
    }
}
