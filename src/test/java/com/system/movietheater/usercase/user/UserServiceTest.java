package com.system.movietheater.usercase.user;

import com.system.movietheater.application.dto.user.DetailingUserDto;
import com.system.movietheater.application.dto.user.RegisterUserDto;
import com.system.movietheater.application.dto.user.UpdateUserDto;
import com.system.movietheater.domain.model.User;
import com.system.movietheater.infrastructure.config.SecurityConfigurations;
import com.system.movietheater.infrastructure.exceptions.EmailAlreadyRegisteredException;
import com.system.movietheater.infrastructure.exceptions.UserNotFoundException;
import com.system.movietheater.infrastructure.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SecurityConfigurations securityConfigurations;

    @Mock
    private User user;

    @Mock
    private RegisterUserDto registerUserDto;

    @Mock
    private DetailingUserDto detailingUserDto;

    @Mock
    private UpdateUserDto updateUserDto;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    void shouldSaveUser() {
        this.registerUserDto = new RegisterUserDto("Nome", "email@email.com", "123456");

        this.user = this.userService.register(this.registerUserDto);

        then(this.userRepository).should().save(this.userArgumentCaptor.capture());
        User userSave = this.userArgumentCaptor.getValue();

        Assertions.assertEquals(this.user.getName(), userSave.getName());
        Assertions.assertEquals(this.user.getEmail(), userSave.getEmail());
    }

    @Test
    void shouldReturnEmailAlreadyRegisteredException() {
        this.registerUserDto = new RegisterUserDto("John", "john@gmail.com", "123456");

        when(userRepository.findEmail(anyString())).thenReturn(user);

        EmailAlreadyRegisteredException emailAlreadyRegisteredException = Assertions.assertThrows(EmailAlreadyRegisteredException.class, () -> {
            userService.register(this.registerUserDto);
        });

        Assertions.assertEquals("Email already exists", emailAlreadyRegisteredException.getMessage());
    }

    @Test
    @WithMockUser(roles = {"MOVIETHEATER"})
    void shouldReturnListUser() {
        userService.listUsers();

        then(userRepository).should().findByActiveTrue();
    }

    @Test
    @WithMockUser(roles = {"MOVIETHEATER"})
    void shouldReturnUniqueUser(){
        Long id = 1L;
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        userService.selectUser(id);

        then(userRepository).should().findById(id);
    }

    @Test
    @WithMockUser
    void shouldUpdateDataUser() {
        this.updateUserDto = new UpdateUserDto(1L,"John", "email@gmail.com");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        userService.updateUser(this.updateUserDto);

        then(user).should().updateData(this.updateUserDto);
    }

    @Test
    @WithMockUser(roles = {"MOVIETHEATER"})
    void shouldReturnUserNotFoundException(){
        when(userRepository.findById(anyLong())).thenThrow(new UserNotFoundException("User not found"));

        UserNotFoundException userNotFoundException = Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.selectUser(this.user.getId());
        });

        System.out.println(userNotFoundException.toString());

        Assertions.assertEquals("User not found", userNotFoundException.getMessage());
    }
}