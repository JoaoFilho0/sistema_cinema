package com.system.movietheater.domain.horary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoraryService {

    @Autowired
    private HoraryRepository horaryRepository;

    public Horary registerHorary(DataRegisterHorary data) {
        var horary = new Horary(data);

        horaryRepository.save(horary);

        return horary;
    }
}
