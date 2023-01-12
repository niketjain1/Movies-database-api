package com.Project.Movie.users;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto createUser(CreateUserDto request){
        UserEntity user = modelMapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return modelMapper.map(userRepository.save(user), UserResponseDto.class);
    }

    public UserResponseDto verifyUser(LoginUserDto request){
        UserEntity user = userRepository.findByUsername(request.getUsername());
        if(user == null){
            throw new RuntimeException("User not found");
        }
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        return modelMapper.map(user, UserResponseDto.class);
    }
}
