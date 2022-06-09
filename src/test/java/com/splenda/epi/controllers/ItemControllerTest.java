package com.splenda.epi.controllers;

import com.splenda.epi.entities.core.Item;
import com.splenda.epi.entities.dtos.ItemEmployeeDTO;
import com.splenda.epi.services.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    @Test
    public void shouldCallItemServiceWhenFindByEmployeeId(){
        List<ItemEmployeeDTO> itemList = new ArrayList<>();
        itemList.add(ItemEmployeeDTO.builder().idItem(1L).build());
        when(itemService.findByIdEmployee(any())).thenReturn(itemList);

        ResponseEntity<List<ItemEmployeeDTO>> result = itemController.findByIdEmployee(1);

        assertEquals(ResponseEntity.ok(itemList), result);
        verify(itemService, times(1)).findByIdEmployee(any());

    }

    @Test
    public void shouldReturnAllItemsWhenFindAll(){
        List<Item> itemList = new ArrayList<>();
        itemList.add(Item.builder().idItem(1L).build());

        when(itemService.findAll()).thenReturn(itemList);

        ResponseEntity<List<Item>> result = itemController.findAll();

        assertEquals(ResponseEntity.ok(itemList), result);
        verify(itemService, times(1)).findAll();

    }
}
