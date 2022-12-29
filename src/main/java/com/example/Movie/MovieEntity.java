package com.example.Movie;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAnyAttribute;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "movies")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(name = "Release_Date", columnDefinition = "Date")
    private LocalDate releaseDate;
    @Column(name = "Genre")
    private String genre;
    @Column(name = "Rating")
    private double rating;


    public MovieEntity(long id, String title, LocalDate releaseDate, String genre, double rating){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public MovieEntity(){}
    public MovieEntity(Long id, String title, LocalDate releaseDate, String genre, double rating){
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.rating = rating;
    }
}

