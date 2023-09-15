package com.system.movietheater.domain.user;

import com.system.movietheater.infra.exception.BadRequestException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(DataRegisterUser data) {
        var user = new User(data);

        userRepository.save(user);

        return user;
    }

    public List<DataListingUser> listUsers() {
        return userRepository.findByActiveTrue();
    }

    public DataDetailingUser selectUser(Long id) {
        var user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return new DataDetailingUser(user);
    }

    public URI generateUri(User user, UriComponentsBuilder uriBuilder) {
        return uriBuilder.path("usuario/{id}").buildAndExpand(user.getId()).toUri();
    }

    public User updateUser(DataUpdateUser data) {
        var user = userRepository.getReferenceById(data.id());
        user.updateData(data);

        return user;
    }

    public User activeAccount(Long id) {
        var user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if(user.getActive()){
            throw new BadRequestException("User account actived");
        }

        user.activeAccount();

        return user;
    }

    public User disableAccount(Long id) {
        var user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if(!user.getActive()) {
            throw new BadRequestException("User account disabled");
        }

        user.disableAccount();

        return user;
    }
}
