package com.system.movietheater.usercase.user;

import com.system.movietheater.application.dto.user.DetailingUserDto;
import com.system.movietheater.application.dto.user.ListingUserDto;
import com.system.movietheater.application.dto.user.RegisterUserDto;
import com.system.movietheater.application.dto.user.UpdateUserDto;
import com.system.movietheater.domain.model.User;
import com.system.movietheater.infrastructure.exceptions.BadRequestException;
import com.system.movietheater.infrastructure.exceptions.EmailAlreadyRegisteredException;
import com.system.movietheater.infrastructure.exceptions.UserNotFoundException;
import com.system.movietheater.infrastructure.persistence.repository.UserRepository;
import com.system.movietheater.infrastructure.config.SecurityConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    public User register(RegisterUserDto data) {
        if (userRepository.findEmail(data.email()) != null) {
            throw new EmailAlreadyRegisteredException("Email already exists");
        }

        var user = new User(data);
        var password = new BCryptPasswordEncoder().encode(user.getPassword());
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
        if (userRepository.findEmail(user.getEmail()) != null) {
            return uriBuilder.path("usuario/{id}").buildAndExpand(user.getId()).toUri();
        }
        throw new UserNotFoundException("User not found");
    }

    public List<ListingUserDto> listUsers(Pageable pagination) {
        return userRepository.findAll(pagination).stream().map(ListingUserDto::new).toList();
    }

    public List<ListingUserDto> listUsers() {
        return userRepository.findByActiveTrue();
    }

    public DetailingUserDto selectUser(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        return new DetailingUserDto(user);
    }

    public User updateUser(UpdateUserDto data) {
        var user = userRepository.findById(data.id()).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.updateData(data);

        return user;
    }

    public User activeAccount(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        if(user.getActive()){
            throw new BadRequestException("User account active");
        }

        user.statusAccount(false);

        return user;
    }

    public User disableAccount(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        if(!user.getActive()) {
            throw new BadRequestException("User account disabled");
        }

        user.statusAccount(true);

        return user;
    }
}
