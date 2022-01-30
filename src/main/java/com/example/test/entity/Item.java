package com.example.test.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //단일 테이블 전략 설정
@DiscriminatorColumn(name = "DTYPE") // 타입 나눠주기 위한 설정
@Getter @Setter
@Entity
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
