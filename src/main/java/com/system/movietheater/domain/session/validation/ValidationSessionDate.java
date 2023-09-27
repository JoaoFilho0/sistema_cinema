package com.system.movietheater.domain.session.validation;

import com.system.movietheater.domain.session.Session;
import com.system.movietheater.infra.exception.SessionDateInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class ValidationSessionDate implements ValidationSession{

    @Autowired
    private ValidationSessionHorary validationSessionHorary;

    public void validate(Session data) {
        validationSessionHorary.validate(data);

        List<Integer> sessionDate = Arrays.stream(data.getDate().split("-")).map(Integer::parseInt).toList();
        LocalDate today = LocalDate.now();

        int diference = sessionDate.get(0) - today.getYear();

        if (diference == 0) {
            diference = sessionDate.get(1) - today.getMonthValue();

            if (diference == 0) {
                diference = sessionDate.get(2) - today.getDayOfMonth();

                if (diference <= 1) {
                    throw new SessionDateInvalidException("Session date is very close");
                }
            } else if (diference < 0) {
                throw new SessionDateInvalidException("Date has passed");
            }
        } else if (diference < 0) {
            throw new SessionDateInvalidException("Date has passed");
        }
    }
}
