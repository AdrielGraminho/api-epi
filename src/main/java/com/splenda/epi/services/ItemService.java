package com.splenda.epi.services;

import com.splenda.epi.entities.core.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();

    Item findById(Long idItem);
}
