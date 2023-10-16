package com.system.movietheater.usercase.horary;

import com.system.movietheater.application.dto.horary.RegisterHoraryDto;
import com.system.movietheater.application.dto.horary.UpdateHoraryDto;
import com.system.movietheater.infrastructure.validations.horary.ValidationHorary;
import com.system.movietheater.domain.model.Horary;
import com.system.movietheater.infrastructure.persistence.repository.MovieTheaterRepository;
import com.system.movietheater.infrastructure.exceptions.HoraryAlreadyExistsException;
import com.system.movietheater.infrastructure.exceptions.HoraryNotFoundException;
import com.system.movietheater.infrastructure.exceptions.MovieTheaterNotFoundException;
import com.system.movietheater.infrastructure.persistence.repository.HoraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class HoraryService {

    @Autowired
    private HoraryRepository horaryRepository;

    @Autowired
    private MovieTheaterRepository movieTheaterRepository;

    @Autowired
    private List<ValidationHorary> validations;

    public Horary registerHorary(RegisterHoraryDto data) {
        if (horaryRepository.findMovieTheaterAndDay(data.movieTheater().id(), data.day()) != null) {
            throw new HoraryAlreadyExistsException();
        }

        movieTheaterRepository.findById(data.movieTheater().id()).orElseThrow(MovieTheaterNotFoundException::new);
        var horary = new Horary(data);

        validations.forEach(validation -> validation.validate(horary));

        horary.setDay(horary.getDay().toLowerCase(Locale.ROOT));

        horaryRepository.save(horary);

        return horary;
    }

    public Horary updateHorary(UpdateHoraryDto data) {
        if (horaryRepository.findMovieTheaterAndDay(data.movieTheater().id(), data.day()) != null) {
            throw new HoraryAlreadyExistsException();
        }

        var horary = horaryRepository.findById(data.id()).orElseThrow(HoraryNotFoundException::new);

        movieTheaterRepository.findById(data.movieTheater().id()).orElseThrow(MovieTheaterNotFoundException::new);

        horary.updateData(data);

        return horary;
    }

    public List<Horary> list(Long id) {
        movieTheaterRepository.findById(id).orElseThrow(MovieTheaterNotFoundException::new);

        return horaryRepository.findByMovieTheaterId(id);
    }

    public void deleteHorary(Long id) {
        var horary = horaryRepository.findById(id).orElseThrow(HoraryNotFoundException::new);

        horaryRepository.delete(horary);
    }
}
