package com.splenda.epi.services;

import com.splenda.epi.entities.core.Item;
import com.splenda.epi.entities.dtos.ItemEmployeeDTO;

import java.util.List;

public interface ItemService {
    List<Item> findAll();

    Item findById(Long idItem);

    List<ItemEmployeeDTO> findByIdEmployee(Integer idEmployee);
}
