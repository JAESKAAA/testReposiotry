package com.example.test.mappedSuperclass;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "ALBUM_ID"))
public class Album1 extends BaseEntity {

private String artist;
}
