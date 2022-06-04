package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.Item;
import com.splenda.epi.entities.dtos.ItemEmployeeDTO;
import com.splenda.epi.entities.exceptions.ItemNoutFoundException;
import com.splenda.epi.repository.ItemRepository;
import com.splenda.epi.services.EmployeeService;
import com.splenda.epi.services.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final EmployeeService employeeService;

    public ItemServiceImpl(ItemRepository itemRepository, EmployeeService employeeService) {
        this.itemRepository = itemRepository;
        this.employeeService = employeeService;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(Long idItem) {
        return itemRepository.findById(idItem).orElseThrow(()-> new ItemNoutFoundException("item.not-found"));
    }

    @Override
    public List<ItemEmployeeDTO> findByIdEmployee(Integer idEmployee) {
        employeeService.findById(Long.valueOf(idEmployee));
        return itemRepository.findByIdEmployee(Long.valueOf(idEmployee));
    }
}
