package com.Project.Movie.security.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtServiceTest {
    private JwtService jwtService(){
        return new JwtService();
    }

    @Test
    public void createJwt_works_with_username(){
        var jwtService = jwtService();
        var jwtString =  jwtService.createJwt("Nick");
        var username = jwtService.getUsernameForJwt(jwtString);

        assertEquals("Nick", username);
    }
    @Test
    public void createJwt_errors_with_null(){
        var jwtService = jwtService();

        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            var jwtString = jwtService.createJwt(null);
        });
    }
}
