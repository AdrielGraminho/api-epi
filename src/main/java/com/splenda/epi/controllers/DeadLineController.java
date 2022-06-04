package com.splenda.epi.controllers;

import com.splenda.epi.entities.dtos.DeadLineDTO;
import com.splenda.epi.services.DeadlineService;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<DeadLineDTO>> findDeadLineByIdBusinessUnitAndDate(
            @RequestParam(value = "idBusinessUnit", required = false)  Integer idBusinessUnit,
            @RequestParam("date") LocalDate date

    ){
        return ResponseEntity.ok(deadlineService.findDeadLineByIdBusinessUnitAndDate(idBusinessUnit, date));
    }
}
