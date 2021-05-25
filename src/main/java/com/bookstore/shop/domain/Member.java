package com.bookstore.shop.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    private String email;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    private LocalDateTime createDate;

}
