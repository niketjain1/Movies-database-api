package com.Project.Movie.users;

import com.Project.Movie.security.authtoken.AuthTokenService;
import com.Project.Movie.security.jwt.JwtService;
import com.Project.Movie.users.dtos.CreateUserDto;
import com.Project.Movie.users.dtos.LoginUserDto;
import com.Project.Movie.users.dtos.UserResponseDto;
import com.Project.Movie.security.jwt.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthTokenService authTokenService;
    private JwtService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthTokenService authTokenService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authTokenService = authTokenService;
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
        var token = jwtService.createJwt(savedUser.getUsername());
        response.setToken(token);
        return response;
    }

    public UserResponseDto verifyUser(LoginUserDto request){
        UserEntity user = userRepository.findByUsername(request.getUsername());
        if(user == null){
            throw new RuntimeException("User not found");
        }
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

        var response = modelMapper.map(user, UserResponseDto.class);
        response.setToken(authTokenService.createToken(user));
        return response;
    }

    public UserResponseDto findByUsername(String username){
        UserEntity user = userRepository.findByUsername(username);
        var response = modelMapper.map(user, UserResponseDto.class);
        return response;
    }
}
