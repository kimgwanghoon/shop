package com.bookstore.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    private String email;

    @JsonIgnore //주문조회 v1 무한루프 조치
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createDate;

}
