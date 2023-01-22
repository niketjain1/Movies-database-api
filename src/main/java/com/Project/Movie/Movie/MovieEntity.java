package com.Project.Movie.Movie;

import com.Project.Movie.users.UserEntity;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAnyAttribute;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "movies")
@Data
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "movie_id", unique = true, nullable = false)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(name = "Release_Date")
    private int releaseYear;
    @Column(name = "Genre")
    private String genre;
    @Column(name = "Rating")
    private double rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public MovieEntity(int id, String title, int releaseYear, String genre, double rating, UserEntity user) {
        super();
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;
        this.user = user;
    }
    public MovieEntity(){super();}
}

