package com.splenda.epi.controllers;

import com.splenda.epi.entities.dtos.ItemEmployeeDTO;
import com.splenda.epi.services.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('TOTAL', 'BU')")
    public ResponseEntity<List<ItemEmployeeDTO>> findByIdEmployee(
            @RequestParam Integer idEmployee
    ){
      return ResponseEntity.ok(itemService.findByIdEmployee(idEmployee));
    }

}
