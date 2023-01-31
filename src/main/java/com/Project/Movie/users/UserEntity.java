package com.Project.Movie.users;

import com.Project.Movie.Movie.MovieEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", unique = true, nullable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String password;

    public UserEntity(int id, String username, String email, String password) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @OneToMany(targetEntity = MovieEntity.class,  cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<MovieEntity> movies;
}
