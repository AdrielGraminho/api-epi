package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.Employee;
import com.splenda.epi.entities.exceptions.EmployeNotFoundException;
import com.splenda.epi.repository.EmployeeRepository;
import com.splenda.epi.services.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findByCpf(String cpf) {
        return employeeRepository.findByCpf(cpf).orElseThrow(() -> new EmployeNotFoundException("employee.not-found"));
    }
}
