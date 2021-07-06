package com.bookstore.shop.repository.order.simplequery;


import com.bookstore.shop.dto.SimpleOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<SimpleOrderDto> findOrderDtos() {
        return em.createQuery("select new com.bookstore.shop.api.dto.OrderSimpleQueryDto(o.id,m.name,o.orderDate,o.status,d.address) "+
                "from Order o" +
                " join o.member m" +
                " join o.delivery d", SimpleOrderDto.class)
                .getResultList();
    }
}
