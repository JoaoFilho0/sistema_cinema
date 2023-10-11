package com.system.movietheater;

import com.system.movietheater.domain.Enum.TypeUserEnum;
import com.system.movietheater.domain.model.User;
import com.system.movietheater.usercase.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MovieTheaterApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(MovieTheaterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var dados = userService.listUsers();

		if(dados.isEmpty()) {
			User user = new User();
			user.setName("ADM");
			user.setEmail("adm@adm.com");
			user.setPassword("123456");
			user.setType(List.of(TypeUserEnum.ROLE_ADMIN));

			userService.registerAdm(user);
		}
	}
}
