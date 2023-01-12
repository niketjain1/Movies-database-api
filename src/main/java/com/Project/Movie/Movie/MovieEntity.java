package com.Project.Movie.Movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAnyAttribute;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "movies")
@Getter @Setter
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(name = "Release_Date")
    private int releaseYear;
    @Column(name = "Genre")
    private String genre;
    @Column(name = "Rating")
    private double rating;

    public MovieEntity(String title, double rating){
        this.title = title;
        this.rating = rating;
    }

    public MovieEntity(long id, String title, LocalDate releaseDate, String genre, double rating){}



    public MovieEntity(){}
    public MovieEntity(int id, String title, int releaseYear, String genre, double rating){
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;
    }
}

