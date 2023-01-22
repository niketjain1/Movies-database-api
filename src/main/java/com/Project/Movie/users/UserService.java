package com.Project.Movie.users;


import com.Project.Movie.security.jwt.JwtService;
import com.Project.Movie.users.dtos.CreateUserDto;
import com.Project.Movie.users.dtos.LoginUserDto;
import com.Project.Movie.users.dtos.UserResponseDto;
import com.Project.Movie.security.jwt.JwtService;
import com.Project.Movie.users.user_exception.InvalidPasswordException;
import com.Project.Movie.users.user_exception.InvalidUsernameException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponseDto createUser(CreateUserDto request){
        var user = modelMapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var savedUser = userRepository.save(user);
        var response = modelMapper.map(savedUser, UserResponseDto.class);
        // Option 01: Server Side token ->
        /*
        var token = authTokenService.createToken(savedUser);
        response.setToken(token);
        */
        // Option 02: JWT ->
//        var token = jwtService.createJwt(savedUser.getUsername());
//        response.setToken(token);
        return response;
    }

    public UserResponseDto verifyUser(LoginUserDto request){
        UserEntity user = userRepository.findByUsername(request.getUsername());
        if(user == null && request.getUsername() != user.getUsername()){
            throw new InvalidUsernameException();
        }
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidPasswordException();
        }

        var response = modelMapper.map(user, UserResponseDto.class);
        response.setToken(jwtService.createJwt(response.getUsername()));
        return response;
    }

    public UserResponseDto findByUsername(String username){
        UserEntity user = userRepository.findByUsername(username);
        var response = modelMapper.map(user, UserResponseDto.class);
        return response;
    }

    public void deleteUserById(String username){
        var user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }
}
