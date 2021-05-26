package com.bookstore.shop.domain.item;

import com.bookstore.shop.exception.NotEnoughStockException;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Data
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private LocalDateTime createDate;

    //비즈니스로직
    /* 재고 증가 */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /* 재고 감소 */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock<0){
            throw new NotEnoughStockException("재고 확인 필요");
        }
        this.stockQuantity = restStock;
    }

}
