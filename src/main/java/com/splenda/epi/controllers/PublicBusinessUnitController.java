package com.splenda.epi.controllers;

import com.splenda.epi.entities.core.PublicBusinessUnit;
import com.splenda.epi.services.PublicBusinessUnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/business-unit")
public class PublicBusinessUnitController {
    private final PublicBusinessUnitService publicBusinessUnitService;

    public PublicBusinessUnitController(PublicBusinessUnitService publicBusinessUnitService) {
        this.publicBusinessUnitService = publicBusinessUnitService;
    }

    @GetMapping()
    public ResponseEntity<List<PublicBusinessUnit>> findAllByPermission(){
        return ResponseEntity.ok(publicBusinessUnitService.findByPermissionUser());
    }

}
