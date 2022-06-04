package com.splenda.epi.controllers;

import com.splenda.epi.entities.core.Employee;
import com.splenda.epi.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('TOTAL', 'BU')")
    public ResponseEntity<Employee> findByCpf(
        @RequestParam String cpf
    ){
        return ResponseEntity.ok(employeeService.findByCpf(cpf));
    }
}
