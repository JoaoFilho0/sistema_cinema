package com.system.movietheater.usercase.user;

import com.system.movietheater.application.dto.user.DataDetailingUser;
import com.system.movietheater.application.dto.user.DataListingUser;
import com.system.movietheater.application.dto.user.DataRegisterUser;
import com.system.movietheater.application.dto.user.DataUpdateUser;
import com.system.movietheater.domain.model.User;
import com.system.movietheater.infrastructure.exceptions.BadRequestException;
import com.system.movietheater.infrastructure.exceptions.EmailAlreadyRegisteredException;
import com.system.movietheater.infrastructure.exceptions.UserNotFoundExcpetion;
import com.system.movietheater.infrastructure.persistence.repository.UserRepository;
import com.system.movietheater.infrastructure.config.SecurityConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
        if (userRepository.findEmail(data.email()) != null) {
            throw new EmailAlreadyRegisteredException("Email already exists");
        }

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

    public List<DataListingUser> listUsers(Pageable pagination) {
        return userRepository.findAll(pagination).stream().map(DataListingUser::new).toList();
    }

    public List<DataListingUser> listUsers() {
        return userRepository.findByActiveTrue();
    }

    public DataDetailingUser selectUser(Long id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundExcpetion::new);

        return new DataDetailingUser(user);
    }

    public User updateUser(DataUpdateUser data) {
        var user = userRepository.findById(data.id()).orElseThrow(UserNotFoundExcpetion::new);
        user.updateData(data);

        return user;
    }

    public User activeAccount(Long id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundExcpetion::new);

        if(user.getActive()){
            throw new BadRequestException("User account active");
        }

        user.statusAccount(false);

        return user;
    }

    public User disableAccount(Long id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundExcpetion::new);

        if(!user.getActive()) {
            throw new BadRequestException("User account disabled");
        }

        user.statusAccount(true);

        return user;
    }
}
