package com.Project.Movie.users;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserResponseDto createUser(CreateUserDto request){
        UserEntity user = modelMapper.map(request, UserEntity.class);
        return modelMapper.map(userRepository.save(user), UserResponseDto.class);
    }

    public UserResponseDto verifyUser(LoginUserDto request){
        UserEntity user = userRepository.findByUsername(request.getUsername());
        if(user == null){
            throw new RuntimeException("User not found");
        }
        if(!user.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        return modelMapper.map(user, UserResponseDto.class);
    }
}
