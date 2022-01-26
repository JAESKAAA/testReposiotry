package com.example.test.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Category {

    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

    //예제에는 다대다 관계를 매핑했지만, 실제로는 다대다관계를 연결하여 일대다, 다대일 관계로 풀어주는 테이블을 하나 만드는게 더 좋은 모델링 기법임
    //따라서 다대다 매핑 실습은 생략하도록 함
//    @ManyToMany
//    @JoinTable(name = "CATEGORY_ITEM", joinColumns = @JoinColumn(name = "CATEGORY_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
//    private List<Item> items = new ArrayList<>();


}
