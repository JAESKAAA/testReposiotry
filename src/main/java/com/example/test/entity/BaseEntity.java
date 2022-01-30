package com.example.test.entity;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public class BaseEntity {

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
