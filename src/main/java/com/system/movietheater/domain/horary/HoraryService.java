package com.system.movietheater.domain.horary;

import com.system.movietheater.domain.movietheater.MovieTheaterRepository;
import com.system.movietheater.infra.exception.HoraryAlreadyExistsException;
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

    public Horary registerHorary(DataRegisterHorary data) {
        if (horaryRepository.findMovieTheaterAndDay(data.movieTheater().getId(), data.day()) != null) {
            throw new HoraryAlreadyExistsException();
        }
        var horary = new Horary(data);

        horaryRepository.save(horary);

        return horary;
    }

    public Horary updateHorary(DataUpdateHorary data) {
        var horary = horaryRepository.findById(data.id()).orElseThrow(() -> new EntityNotFoundException("Horary not found"));
        movieTheaterRepository.findById(data.movieTheater().getId()).orElseThrow(() -> new EntityNotFoundException("Movie Theater not found"));

        horary.updateData(data);

        return horary;
    }

    public List<Horary> list(Long id) {
        return horaryRepository.findByMovieTheaterId(id);
    }

    public void deleteHorary(Long id) {
        var horary = horaryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Horary not found"));

        horaryRepository.delete(horary);
    }
}
