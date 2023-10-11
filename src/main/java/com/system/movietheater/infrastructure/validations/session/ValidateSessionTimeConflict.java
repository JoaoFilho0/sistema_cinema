package com.system.movietheater.infrastructure.validations.session;

import com.system.movietheater.domain.model.Session;
import com.system.movietheater.infrastructure.persistence.repository.SessionRepository;
import com.system.movietheater.infrastructure.exceptions.SessionDurationConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class ValidateSessionTimeConflict implements ValidationSession{

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ValidationSessionHorary validationSessionHorary;

    public void validate(Session data) {
        validationSessionHorary.validate(data);

        List<Session> listSession = sessionRepository.findByDate(data.getDate());

        String[] sessionDuration = data.getHorary().split("-");
        List<Integer> sessionInit = Arrays.stream(sessionDuration[0].split(":")).map(Integer::parseInt).toList();
        List<Integer> sessionEnd = Arrays.stream(sessionDuration[1].split(":")).map(Integer::parseInt).toList();

        if (!listSession.isEmpty()) {
            listSession.forEach(sess -> {
                String[] sessDuration = sess.getHorary().split("-");
                List<Integer> sessInit = Arrays.stream(sessDuration[0].split(":")).map(Integer::parseInt).toList();
                List<Integer> sessEnd = Arrays.stream(sessDuration[1].split(":")).map(Integer::parseInt).toList();

                if (sessionInit.get(0) > sessInit.get(0) && sessionInit.get(0) < sessEnd.get(0)) {
                    throw new SessionDurationConflictException();
                } else if (sessionEnd.get(0) > sessInit.get(0) && sessionEnd.get(0) < sessEnd.get(0)) {
                    throw new SessionDurationConflictException();
                } else if (Objects.equals(sessionInit.get(0), sessInit.get(0))) {
                    int diference = sessionInit.get(1) - sessInit.get(1);
                    if (diference < 10 && diference > -10) {
                        throw new SessionDurationConflictException();
                    }
                } else if (Objects.equals(sessionInit.get(0), sessEnd.get(0))) {
                    int diference = sessionInit.get(1) - sessEnd.get(1);

                    if (diference < 10 && diference > -10) {
                        throw new SessionDurationConflictException();
                    }
                } else if (Objects.equals(sessionEnd.get(0), sessInit.get(0))) {
                    int diference = sessionEnd.get(1) - sessInit.get(1);

                    if (diference < 10 && diference > -10) {
                        throw new SessionDurationConflictException();
                    }
                }
            });
        }
    }
}
