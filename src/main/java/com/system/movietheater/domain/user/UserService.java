package com.system.movietheater.domain.user;

import com.system.movietheater.infra.exception.BadRequestException;
import com.system.movietheater.infra.exception.UserNotFoundExcpetion;
import com.system.movietheater.infra.security.SecurityConfigurations;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfigurations securityConfigurations;

    public User register(DataRegisterUser data) {
        var user = new User(data);
        var password = securityConfigurations.passwordEncoder().encode(user.getPassword());
        user.setPassword(password);

        userRepository.save(user);

        return user;
    }

    public void registerAdm(User user) {
        var password = securityConfigurations.passwordEncoder().encode(user.getPassword());
        user.setPassword(password);

        userRepository.save(user);
    }

    public URI generateUri(User user, UriComponentsBuilder uriBuilder) {
        return uriBuilder.path("usuario/{id}").buildAndExpand(user.getId()).toUri();
    }

    public List<DataListingUser> listUsers() {
        return userRepository.findByActiveTrue();
    }

    public DataDetailingUser selectUser(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundExcpetion("User not found"));

        return new DataDetailingUser(user);
    }

    public User updateUser(DataUpdateUser data) {
        var user = userRepository.findById(data.id()).orElseThrow(() -> new UserNotFoundExcpetion("User not found"));
        user.updateData(data);

        return user;
    }

    public User activeAccount(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundExcpetion("User not found"));

        if(user.getActive()){
            throw new BadRequestException("User account actived");
        }

        user.activeAccount();

        return user;
    }

    public User disableAccount(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundExcpetion("User not found"));

        if(!user.getActive()) {
            throw new BadRequestException("User account disabled");
        }

        user.disableAccount();

        return user;
    }
}
