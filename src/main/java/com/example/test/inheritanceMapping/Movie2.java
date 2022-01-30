package com.example.test.inheritanceMapping;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("M")
@PrimaryKeyJoinColumn(name = "MOVIE_ID")
public class Movie2 extends Item2 {

    private String director;
    private String actor;
}
