package com.Project.Movie.Movie;

import com.Project.Movie.users.UserEntity;
import lombok.*;
import org.springframework.security.core.userdetails.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAnyAttribute;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "movies")
@Data
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", unique = true, nullable = false)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(name = "Release_Date")
    private int releaseYear;
    @Column(name = "Genre")
    private String genre;
    @Column(name = "Rating")
    private double rating;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public MovieEntity(int id, String title, int releaseYear, String genre, double rating) {
        super();
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;
    }
    public MovieEntity(){super();}
}

