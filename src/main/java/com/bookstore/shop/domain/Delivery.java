package com.bookstore.shop.domain;

import com.bookstore.shop.domain.status.DeliveryStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
