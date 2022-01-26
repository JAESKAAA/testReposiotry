package com.example.test.mappedSuperclass;

import com.example.test.inheritanceMapping.Item;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "MOVIE_ID")),
    @AttributeOverride(name = "name", column = @Column(name = "MOVIE_NAME"))
})
public class Movie extends BaseEntity {

    private String director;
    private String actor;
}
