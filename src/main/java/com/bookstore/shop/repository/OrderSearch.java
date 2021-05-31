package com.bookstore.shop.repository;

import com.bookstore.shop.domain.status.OrderStatus;
import lombok.Data;

@Data
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
