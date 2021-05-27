package com.bookstore.shop.service;

import com.bookstore.shop.domain.Address;
import com.bookstore.shop.domain.Member;
import com.bookstore.shop.domain.Order;
import com.bookstore.shop.domain.item.Book;
import com.bookstore.shop.domain.item.Item;
import com.bookstore.shop.domain.status.OrderStatus;
import com.bookstore.shop.exception.NotEnoughStockException;
import com.bookstore.shop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        Member member = createMember();
        Item item = createBook("JPA 기본",10000,10);
        int count = 3;

        Long order = orderService.order(member.getId(), item.getId(), count);

        Order getOrder = orderRepository.findId(order);

        assertEquals("상품주문 상태 ORDER", OrderStatus.ORDER,getOrder.getStatus());
        assertEquals("주문 상품 종류수가 정확해야함", 1,getOrder.getOrderItems().size());
        assertEquals("주문가격 = 가격*수량", 10000*count,getOrder.getTotalPrice());
        assertEquals("재고체크", 7,item.getStockQuantity());

    }

    @Test
    public void 상품취소() throws Exception{
        Member member = createMember();
        Item item = createBook("JPA 기본",10000,10);

        int orderCount = 2;
        Long order = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(order);

        Order getOrder = orderRepository.findId(order);

        assertEquals("취소시 상태 cancel",OrderStatus.CANCEL,getOrder.getStatus());
        assertEquals("취소된 상품은 재고가 증가해야한다.",10,item.getStockQuantity());

    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        Member member = createMember();
        Item item = createBook("JPA 기본",10000,10);
        int orderCount = 11;

        orderService.order(member.getId(), item.getId(), orderCount);

        fail("재고수량이 부족한 예외가 발생해야합니다.");


    }


    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("test");
        member.setAddress(new Address("서울","신월로 99","132546"));
        em.persist(member);
        return member;
    }

}