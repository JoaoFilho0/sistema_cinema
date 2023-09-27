package com.system.movietheater.domain.session.validation;

import com.system.movietheater.domain.horary.Horary;
import com.system.movietheater.domain.horary.HoraryRepository;
import com.system.movietheater.domain.movie.MovieRepository;
import com.system.movietheater.domain.session.Session;
import com.system.movietheater.infra.exception.SessionDayInvalidException;
import com.system.movietheater.infra.exception.SessionHoraryInvalidException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ValidateMovieTheaterOpeningHours {

    @Autowired
    private HoraryRepository horaryRepository;


    public void validate(Session data) {
        List<Horary> listHorary = horaryRepository.findByMovieTheaterId(data.getRoom().getMovieTheater().getId());

        String[] dateSession = data.getDate().split("-");

        DayOfWeek date = LocalDate.of(Integer.parseInt(dateSession[0]), Integer.parseInt(dateSession[1]), Integer.parseInt(dateSession[2]))
                .getDayOfWeek();

        String dayWeek = findDayWeek(date);

        listHorary.forEach(hor -> {
            if (Objects.equals(hor.getDay(), dayWeek)) {
                String[] sessionDuration = data.getHorary().split("-");
                List<Integer> sessionInit = Arrays.stream(sessionDuration[0].split(":")).map(Integer::parseInt).toList();
                List<Integer> sessionEnd = Arrays.stream(sessionDuration[1].split(":")).map(Integer::parseInt).toList();

                String[] movieTheaterHorary = hor.getHorary().split("-");
                List<Integer> movieTheaterHoraryOpen = Arrays.stream(movieTheaterHorary[0].split(":")).map(Integer::parseInt).toList();
                List<Integer> movieTheaterHoraryClosed = Arrays.stream(movieTheaterHorary[1].split(":")).map(Integer::parseInt).toList();

                if(sessionInit.get(0) < movieTheaterHoraryOpen.get(0) || sessionInit.get(0) >= movieTheaterHoraryClosed.get(0)) {
                    throw new SessionHoraryInvalidException();
                } else if (Objects.equals(sessionInit.get(0), movieTheaterHoraryOpen.get(0))) {
                    if(sessionInit.get(1) < movieTheaterHoraryOpen.get(1)) {
                        throw new SessionHoraryInvalidException();
                    }
                }

                if (sessionEnd.get(0) <= movieTheaterHoraryOpen.get(0) && sessionEnd.get(0) > movieTheaterHoraryClosed.get(0)) {
                    throw new SessionHoraryInvalidException();
                } else if (Objects.equals(sessionEnd.get(0), movieTheaterHoraryClosed.get(0))) {
                    if (sessionEnd.get(1) > movieTheaterHoraryClosed.get(1)) {
                        throw new SessionHoraryInvalidException();
                    }
                }
                return;
            }
        });

        throw new SessionDayInvalidException();
    }

    private String findDayWeek(DayOfWeek date) {
        switch (date) {
            case MONDAY -> {
                return "Segunda-feira";
            }

            case TUESDAY -> {
                return "Terça-feira";
            }

            case WEDNESDAY -> {
                return "Quarta-feira";
            }

            case THURSDAY -> {
                return "Quinta-feira";
            }

            case FRIDAY -> {
                return "Sexta-feira";
            }

            case SATURDAY -> {
                return "Sábado-feira";
            }

            case SUNDAY -> {
                return "Domingo-feira";
            }

            default -> {
                return null;
            }
        }
    }
}
