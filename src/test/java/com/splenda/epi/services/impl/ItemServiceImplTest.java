package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.Employee;
import com.splenda.epi.entities.core.Item;
import com.splenda.epi.entities.exceptions.ItemNoutFoundException;
import com.splenda.epi.repository.ItemRepository;
import com.splenda.epi.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private EmployeeService employeeService;

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

    @Test
    public void shouldReturnExceptionWhenFindByIdAndNotFoundItem(){
        when(itemRepository.findById(anyLong())).thenReturn(Optional.empty());

        String exceptionKey = "";
        try {
            itemService.findById(1L);
        }catch (ItemNoutFoundException itemNoutFoundException){
            exceptionKey = itemNoutFoundException.getKey();
        }

        assertEquals("item.not-found", exceptionKey);
        verify(itemRepository, times(1)).findById(anyLong());
    }

    @Test
    public void shouldReturnItemWhenFindById(){
        Item item = Item.builder().idItem(1L).build();
        when(itemRepository.findById(anyLong())).thenReturn(Optional.of(item));

        Item result = itemService.findById(1l);

        assertEquals(item, result);
        verify(itemRepository, times(1)).findById(anyLong());
    }

    @Test
    public void shouldReturnItemListWhenFindByEmployee(){
        List<Item> itemList = new ArrayList<>();
        itemList.add(Item.builder().idItem(1L).build());
        when(employeeService.findById(anyLong())).thenReturn(new Employee());
        when(itemRepository.findByIdEmployee(anyLong())).thenReturn(itemList);

        List<Item> result = itemService.findByIdEmployee(1);

        assertEquals(itemList, result);
        verify(employeeService, times(1)).findById(anyLong());
        verify(itemRepository, times(1)).findByIdEmployee(anyLong());

    }
}
