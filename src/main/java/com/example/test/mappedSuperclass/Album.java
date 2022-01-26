package com.example.test.mappedSuperclass;

import com.example.test.inheritanceMapping.Item;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "ALBUM_ID"))
public class Album extends BaseEntity {

private String artist;
}
