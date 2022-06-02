package com.splenda.epi.controllers;

import com.splenda.epi.entities.core.PublicBusinessUnit;
import com.splenda.epi.services.PublicBusinessUnitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PublicBusinessUnitControllerTest {

    @InjectMocks
    private PublicBusinessUnitController publicBusinessUnitController;

    @Mock
    private PublicBusinessUnitService publicBusinessUnitService;

    @Test
    public void shouldBeReturnPublicBusinessUnitListWhenFindAllByPermission(){
        List<PublicBusinessUnit> publicBusinessUnitList = new ArrayList<>();
        PublicBusinessUnit publicBusinessUnit = PublicBusinessUnit.builder().idBusinessUnit(1L).build();
        publicBusinessUnitList.add(publicBusinessUnit);

        when(publicBusinessUnitService.findByPermissionUser()).thenReturn(publicBusinessUnitList);

        ResponseEntity<List<PublicBusinessUnit>> result = publicBusinessUnitController.findAllByPermission();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(publicBusinessUnitList, result.getBody());
        verify(publicBusinessUnitService, times(1)).findByPermissionUser();

    }

}
