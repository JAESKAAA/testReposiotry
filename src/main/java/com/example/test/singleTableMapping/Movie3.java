package com.example.test.singleTableMapping;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("M")
@PrimaryKeyJoinColumn(name = "MOVIE_ID")
public class Movie3 extends Item3 {

    private String director;
    private String actor;
}
