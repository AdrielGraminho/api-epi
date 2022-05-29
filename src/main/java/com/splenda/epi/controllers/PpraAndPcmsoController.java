package com.splenda.epi.controllers;

import com.splenda.epi.entities.core.PpraAndPcmso;
import com.splenda.epi.services.PpraAndPcmsoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ppra-pcmso")
public class PpraAndPcmsoController {

    private final PpraAndPcmsoService ppraAndPcmsoService;

    public PpraAndPcmsoController(PpraAndPcmsoService ppraAndPcmsoService) {
        this.ppraAndPcmsoService = ppraAndPcmsoService;
    }

    @GetMapping()
    public ResponseEntity<List<PpraAndPcmso>> findLastPpraAndPcmsoByUnitId(@RequestParam Integer idBusinessUnit){
        return ResponseEntity.ok(ppraAndPcmsoService.findLastPpraAndPcmsoByUnitId(idBusinessUnit));
    }
}
