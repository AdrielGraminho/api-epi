package com.splenda.epi.controllers;

import com.splenda.epi.entities.dtos.DeadLineDTO;
import com.splenda.epi.services.DeadlineService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/deadline")
public class DeadLineController {
    private final DeadlineService deadlineService;

    public DeadLineController(DeadlineService deadlineService) {
        this.deadlineService = deadlineService;
    }

    @GetMapping("/day")
    @PreAuthorize("hasAnyAuthority('TOTAL', 'BU')")
    ResponseEntity<List<DeadLineDTO>> findDeadLineByIdBusinessUnitAndDate(
            @RequestParam(value = "idBusinessUnit", required = false)  Integer idBusinessUnit,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("date") LocalDate date

    ){
        return ResponseEntity.ok(deadlineService.findDeadLineByIdBusinessUnitAndDate(idBusinessUnit, date));
    }

    @GetMapping("/expiration")
    @PreAuthorize("hasAnyAuthority('TOTAL', 'BU')")
    ResponseEntity<List<LocalDate>> findAllDateByUserPermission(){
        return ResponseEntity.ok(deadlineService.findAllDateByUserPermission());
    }
}
