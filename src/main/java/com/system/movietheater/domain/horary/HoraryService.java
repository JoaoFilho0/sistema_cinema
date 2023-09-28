package com.system.movietheater.domain.horary;

import com.system.movietheater.domain.horary.validation.ValidationHorary;
import com.system.movietheater.domain.movietheater.MovieTheaterRepository;
import com.system.movietheater.infra.exception.HoraryAlreadyExistsException;
import com.system.movietheater.infra.exception.HoraryNotFoundException;
import com.system.movietheater.infra.exception.MovieTheaterNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoraryService {

    @Autowired
    private HoraryRepository horaryRepository;

    @Autowired
    private MovieTheaterRepository movieTheaterRepository;

    @Autowired
    private List<ValidationHorary> validations;

    public Horary registerHorary(DataRegisterHorary data) {
        if (horaryRepository.findMovieTheaterAndDay(data.movieTheater().getId(), data.day()) != null) {
            throw new HoraryAlreadyExistsException();
        }

        movieTheaterRepository.findById(data.movieTheater().getId()).orElseThrow(MovieTheaterNotFoundException::new);
        var horary = new Horary(data);

        validations.forEach(validation -> validation.validate(horary));

        horaryRepository.save(horary);

        return horary;
    }

    public Horary updateHorary(DataUpdateHorary data) {
        var horary = horaryRepository.findById(data.id()).orElseThrow(HoraryNotFoundException::new);

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
