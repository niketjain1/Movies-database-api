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
@Entity(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "user_id", unique = true, nullable = false)
    private int id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    public UserEntity(int id, String username, String email, String password) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @OneToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<MovieEntity> movies = new ArrayList<>();
}
