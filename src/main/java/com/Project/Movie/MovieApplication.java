package com.Project.Movie;

import com.Project.Movie.Movie.MovieEntity;
import com.Project.Movie.Movie.MovieRepository;
import com.Project.Movie.users.UserEntity;
import com.Project.Movie.users.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MovieApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
//		UserEntity user = new UserEntity();
//		user.setId(131);
//		user.setEmail("testing131@gmail.com");
//		user.setUsername("testing131");
//		user.setPassword("testingPass131");
//		userRepository.save(user);
//
//		MovieEntity movie1 = new MovieEntity(111, "testMovie", 2022, "test", 8.25, user);
//		MovieEntity movie2 = new MovieEntity(112, "testMovie1", 2021, "comedy", 7.1, user);
//		movieRepository.save(movie1);
//		movieRepository.save(movie2);
	}
}