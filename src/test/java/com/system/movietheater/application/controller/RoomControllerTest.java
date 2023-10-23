package com.system.movietheater.application.controller;

import com.system.movietheater.application.dto.movietheater.ListingMovieTheaterDto;
import com.system.movietheater.application.dto.room.ListingRoomDto;
import com.system.movietheater.application.dto.room.RegisterRoomDto;
import com.system.movietheater.application.dto.room.UpdateRoomDto;
import com.system.movietheater.domain.model.Address;
import com.system.movietheater.domain.model.MovieTheater;
import com.system.movietheater.domain.model.Room;
import com.system.movietheater.infrastructure.persistence.repository.RoomRepository;
import com.system.movietheater.usercase.room.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class RoomControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoomService roomService;

    @Autowired
    private JacksonTester<RegisterRoomDto> registerRoomDto;

    @Autowired
    private JacksonTester<UpdateRoomDto> updateRoomDto;

    @Autowired
    private JacksonTester<ListingRoomDto> listingRoomDto;

    @Test
    @WithMockUser(roles = {"MOVIETHEATER"})
    void shouldReturnError400ForRegistrationRequestWithErrors() throws Exception {
        var response = mvc.perform(
                post("/cinema/sala")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @WithMockUser(roles = {"MOVIETHEATER"})
    void shouldReturnCode201ForRegistrationRequest() throws Exception {
        MovieTheater movieTheater = new MovieTheater();
        movieTheater.setId(1L);
        movieTheater.setName("Nome");
        movieTheater.setAddress(new Address());

        var listingData = new Room();
        listingData.setNumber(1);
        listingData.setSeats(30);

        when(roomService.registerRoom(any())).thenReturn(listingData);

        var response = mvc.perform(
                post("/cinema/sala")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(registerRoomDto.write(
                                new RegisterRoomDto(1, 30, new ListingMovieTheaterDto(movieTheater))
                        ).getJson()))
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var json = listingRoomDto.write(
                new ListingRoomDto(listingData)
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(json);
    }

    @Test
    @WithMockUser(roles = {"MOVIETHEATER"})
    void shouldReturnCode200AndReturnListRooms() throws Exception {
        List<Room> rooms = new ArrayList<>();

        when(roomService.listRooms(anyLong())).thenReturn(rooms);

        var response = mvc.perform(
                get("/cinema/sala/{movie_theater_id}", 1)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser(roles = {"MOVIETHEATER"})
    void shouldReturnCode200WithDataUser() throws Exception {
        Room room = new Room();
        MovieTheater movieTheater = new MovieTheater();
        movieTheater.setId(1L);

        when(roomService.updateRoom(any())).thenReturn(room);

        var response = mvc.perform(
                put("/cinema/sala")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(updateRoomDto.write(
                                new UpdateRoomDto(1L, 2, 30, new ListingMovieTheaterDto(movieTheater))
                        ).getJson()))
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser(roles = {"MOVIETHEATER"})
    void shouldReturnCode204() throws Exception {
        when(roomService.listRooms(anyLong())).thenReturn(null);

        var response = mvc.perform(
                delete("/cinema/sala/{id}", 1)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}