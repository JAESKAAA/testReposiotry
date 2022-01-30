package com.example.test.mappedSuperclass;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "MOVIE_ID")),
    @AttributeOverride(name = "name", column = @Column(name = "MOVIE_NAME"))
})
public class Movie1 extends BaseEntity {

    private String director;
    private String actor;
}
