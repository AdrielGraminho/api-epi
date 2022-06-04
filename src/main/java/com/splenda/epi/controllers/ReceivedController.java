package com.splenda.epi.controllers;

import com.splenda.epi.services.ReceivedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/received")
public class ReceivedController {
    private final ReceivedService receivedService;

    public ReceivedController(ReceivedService receivedService) {
        this.receivedService = receivedService;
    }

    @PostMapping
    public ResponseEntity save(@RequestParam Integer idExit){
        receivedService.save(idExit);
        return ResponseEntity.ok().build();
    }

}
