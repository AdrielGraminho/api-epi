package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.Item;
import com.splenda.epi.entities.exceptions.ItemNoutFoundException;
import com.splenda.epi.repository.ItemRepository;
import com.splenda.epi.services.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(Long idItem) {
        return itemRepository.findById(idItem).orElseThrow(()-> new ItemNoutFoundException("item.not-found"));
    }
}
