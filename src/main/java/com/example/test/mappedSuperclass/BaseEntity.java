package com.example.test.mappedSuperclass;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime crete_at;
    private LocalDateTime update_at;

}
