package com.bookstore.shop.api;

import com.bookstore.shop.api.dto.SimpleOrderDto;
import com.bookstore.shop.domain.Order;
import com.bookstore.shop.repository.OrderRepository;
import com.bookstore.shop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
        List<Order> results = orderRepository.findAll(new OrderSearch());
        for(Order order : results){
            order.getMember().getName();  //Lazy 강제 초기화
            order.getDelivery().getAddress();  //Lazy 강제 초기화
        }
        return results;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAll(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream().map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream().map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
    }
}
