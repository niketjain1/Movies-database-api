package com.Project.Movie.users;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;
    private String email;
    private String password;
}
