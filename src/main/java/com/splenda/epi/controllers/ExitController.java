package com.splenda.epi.controllers;

import com.splenda.epi.entities.dtos.InputExitDTO;
import com.splenda.epi.services.ExitService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/exit")
public class ExitController {

    private final ExitService exitService;


    public ExitController(ExitService exitService) {
        this.exitService = exitService;
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('TOTAL', 'BU')")
    ResponseEntity save(@RequestBody @Valid InputExitDTO inputExitDTO){
        exitService.save(inputExitDTO);
        return ResponseEntity.ok().build();
    }

}
