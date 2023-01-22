package com.Project.Movie.users;

import com.Project.Movie.users.dtos.CreateUserDto;
import com.Project.Movie.users.dtos.LoginUserDto;
import com.Project.Movie.users.dtos.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserResponseDto> createUser(
            @RequestBody CreateUserDto request
    ){
        var createdUser = userService.createUser(request);
        return ResponseEntity.created(URI.create("/users/"+ createdUser.getId())).body(createdUser);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        userService.deleteUserById(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> verifyUser(
            @RequestBody LoginUserDto request
    ){
        var verifiedUser = userService.verifyUser(request);
        return ResponseEntity.ok(verifiedUser);
    }
}
