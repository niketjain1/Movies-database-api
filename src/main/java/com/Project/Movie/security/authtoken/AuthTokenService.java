package com.Project.Movie.security.authtoken;

import com.Project.Movie.users.UserEntity;
import com.Project.Movie.users.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {
    private AuthTokenRepository authTokenRepository;
    private UserRepository userRepository;

    public AuthTokenService(AuthTokenRepository authTokenRepository, UserRepository userRepository) {
        this.authTokenRepository = authTokenRepository;
        this.userRepository = userRepository;
    }

    public String createToken(UserEntity user){
        var token = new AuthTokenEntity();
        token.setUser(user);
        authTokenRepository.save(token);
        return token.getToken().toString();
    }

    public UserEntity getUserFromUser(String token){
        var authToken = authTokenRepository.findById(token).orElseThrow();
        return authToken.getUser();
    }
}
