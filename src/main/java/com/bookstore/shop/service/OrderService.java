package com.bookstore.shop.service;

import com.bookstore.shop.domain.Delivery;
import com.bookstore.shop.domain.Member;
import com.bookstore.shop.domain.Order;
import com.bookstore.shop.domain.OrderItem;
import com.bookstore.shop.domain.item.Item;
import com.bookstore.shop.repository.ItemRepository;
import com.bookstore.shop.repository.MemberRepository;
import com.bookstore.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        Member member = memberRepository.find_id(memberId);
        Item item = itemRepository.findId(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);
        //주문저장
        orderRepository.save(order);

        return order.getId();
    }

    //취소
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 조회
        Order order = orderRepository.findId(orderId);
        //주문취소
        order.cancel();
    }


}
