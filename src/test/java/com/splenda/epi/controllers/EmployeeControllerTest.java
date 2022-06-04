package com.splenda.epi.controllers;

import com.splenda.epi.entities.core.Employee;
import com.splenda.epi.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeService employeeService;

    @Test
    public void shouldReturnEmployeeByCpf(){
        Employee employee = Employee.builder().id(1L).build();
        when(employeeService.findByCpf(any())).thenReturn(employee);

        ResponseEntity<Employee> result = employeeController.findByCpf("123456");

        assertEquals(ResponseEntity.ok(employee), result);
        verify(employeeService, times(1)).findByCpf(any());
    }
}
