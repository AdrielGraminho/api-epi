package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.Employee;
import com.splenda.epi.entities.exceptions.EmployeNotFoundException;
import com.splenda.epi.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void shouldReturnEmployeeWhenFindByCpf(){
        Employee employee = Employee.builder().id(1L).build();
        when(employeeRepository.findByCpf(any())).thenReturn(Optional.of(employee));

        Employee result = employeeService.findByCpf("123456");

        assertEquals(employee, result);
        verify(employeeRepository, times(1)).findByCpf(any());
    }

    @Test
    public void shouldReturnExceptionWhenFindByCpfAndNotFoundEmployee(){
        when(employeeRepository.findByCpf(any())).thenReturn(Optional.empty());

        String exceptionKey = "";
        try {
            employeeService.findByCpf("123456");
        }catch (EmployeNotFoundException employeNotFoundException){
            exceptionKey = employeNotFoundException.getKey();
        }

        assertEquals("employee.not-found", exceptionKey);
        verify(employeeRepository, times(1)).findByCpf(any());
    }

}
