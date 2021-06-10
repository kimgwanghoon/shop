package com.bookstore.shop.service;

import com.bookstore.shop.domain.item.Item;
import com.bookstore.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){

        Item findId = itemRepository.findId(itemId);
        findId.setPrice(price);
        findId.setName(name);
        findId.setStockQuantity(stockQuantity);

    }

    public Item findId(Long itemId){
        return itemRepository.findId(itemId);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }
}
