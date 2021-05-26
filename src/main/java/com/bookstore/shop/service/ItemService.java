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

    public Item findId(Long itemId){
        return itemRepository.findId(itemId);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }
}
