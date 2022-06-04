package com.splenda.epi.services;

import com.splenda.epi.entities.core.Employee;

public interface EmployeeService {
    Employee findByCpf(String cpf);

    Employee findById(Long idEmployee);
}
