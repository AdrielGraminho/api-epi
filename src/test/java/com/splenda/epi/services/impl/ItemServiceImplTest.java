package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.Item;
import com.splenda.epi.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void shouldReturnAllItemWhenFindAllItems(){
        List<Item> itemList = new ArrayList<>();
        Item item = Item.builder().idItem(1L).build();
        itemList.add(item);

        when(itemRepository.findAll()).thenReturn(itemList);

        List<Item> result = itemService.findAll();

        assertEquals(itemList, result);
        verify(itemRepository, times(1)).findAll();
    }
}
