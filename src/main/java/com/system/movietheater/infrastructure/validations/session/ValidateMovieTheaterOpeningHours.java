package com.system.movietheater.infrastructure.validations.session;

import com.system.movietheater.domain.model.Horary;
import com.system.movietheater.infrastructure.persistence.repository.HoraryRepository;
import com.system.movietheater.domain.model.Session;
import com.system.movietheater.infrastructure.exceptions.HoraryInvalidException;
import com.system.movietheater.infrastructure.exceptions.SessionDayInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ValidateMovieTheaterOpeningHours implements ValidationSession{

    @Autowired
    private HoraryRepository horaryRepository;

    @Autowired
    private ValidationSessionHorary validationSessionHorary;

    public void validate(Session data) {
        validationSessionHorary.validate(data);

        List<Horary> listHorary = horaryRepository.findByMovieTheaterId(data.getRoom().getMovieTheater().getId());

        String[] sessionDuration = data.getHorary().split("-");
        List<Integer> sessionInit = Arrays.stream(sessionDuration[0].split(":")).map(Integer::parseInt).toList();
        List<Integer> sessionEnd = Arrays.stream(sessionDuration[1].split(":")).map(Integer::parseInt).toList();

        String[] dateSession = data.getDate().split("-");

        DayOfWeek date = LocalDate.of(Integer.parseInt(dateSession[0]), Integer.parseInt(dateSession[1]), Integer.parseInt(dateSession[2]))
                .getDayOfWeek();

        System.out.println(date.toString());

        String dayWeek = findDayWeek(date);

        AtomicInteger horaryMovieTheaterExists = new AtomicInteger(1);

        listHorary.forEach(hor -> {
            System.out.println("banco de dados: " + hor.getDay() +" | código: " + dayWeek);
            if (Objects.equals(hor.getDay(), dayWeek)) {
                horaryMovieTheaterExists.set(2);

                String[] movieTheaterHorary = hor.getHorary().split("-");
                List<Integer> movieTheaterHoraryOpen = Arrays.stream(movieTheaterHorary[0].split(":")).map(Integer::parseInt).toList();
                List<Integer> movieTheaterHoraryClosed = Arrays.stream(movieTheaterHorary[1].split(":")).map(Integer::parseInt).toList();

                if(sessionInit.get(0) < movieTheaterHoraryOpen.get(0) || sessionInit.get(0) >= movieTheaterHoraryClosed.get(0)) {
                    throw new HoraryInvalidException("Outside cinema opening hours");
                } else if (Objects.equals(sessionInit.get(0), movieTheaterHoraryOpen.get(0))) {
                    if(sessionInit.get(1) < movieTheaterHoraryOpen.get(1)) {
                        throw new HoraryInvalidException("Outside cinema opening hours");
                    }
                }

                if (sessionEnd.get(0) <= movieTheaterHoraryOpen.get(0) && sessionEnd.get(0) > movieTheaterHoraryClosed.get(0)) {
                    throw new HoraryInvalidException("Outside cinema opening hours");
                } else if (Objects.equals(sessionEnd.get(0), movieTheaterHoraryClosed.get(0))) {
                    if (sessionEnd.get(1) > movieTheaterHoraryClosed.get(1)) {
                        throw new HoraryInvalidException("Outside cinema opening hours");
                    }
                }
                return;
            }
        });

        if (horaryMovieTheaterExists.get() == 1) {
            throw new SessionDayInvalidException();
        }
    }

    private String findDayWeek(DayOfWeek date) {
        switch (date) {
            case MONDAY -> {
                return "segunda-feira";
            }

            case TUESDAY -> {
                return "terça-feira";
            }

            case WEDNESDAY -> {
                return "quarta-feira";
            }

            case THURSDAY -> {
                return "quinta-feira";
            }

            case FRIDAY -> {
                return "sexta-feira";
            }

            case SATURDAY -> {
                return "sábado";
            }

            case SUNDAY -> {
                return "domingo";
            }

            default -> {
                return null;
            }
        }
    }
}