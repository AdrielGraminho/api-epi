package com.splenda.epi.services.impl;

import com.splenda.epi.entities.core.Employee;
import com.splenda.epi.entities.core.Exit;
import com.splenda.epi.entities.core.Item;
import com.splenda.epi.entities.core.User;
import com.splenda.epi.entities.dtos.ExitItemDTO;
import com.splenda.epi.entities.dtos.InputExitDTO;
import com.splenda.epi.entities.exceptions.ExitNotFoundException;
import com.splenda.epi.repository.ExitRepository;
import com.splenda.epi.services.*;
import com.splenda.epi.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExitServiceImpl implements ExitService {

    private final ExitRepository exitRepository;

    private final PublicBusinessUnitService publicBusinessUnitService;

    private final ItemService itemService;

    private final EmployeeService employeeService;

    private final UserService userService;

    public ExitServiceImpl(ExitRepository exitRepository, PublicBusinessUnitService publicBusinessUnitService, ItemService itemService, EmployeeService employeeService, UserService userService) {
        this.exitRepository = exitRepository;
        this.publicBusinessUnitService = publicBusinessUnitService;
        this.itemService = itemService;
        this.employeeService = employeeService;
        this.userService = userService;
    }

    @Override
    public List<LocalDate> findAllDateNotReceivedByBusinessUnit(Integer idBusinessUnit) {

        publicBusinessUnitService.findPublicBusinessUnitById(Long.valueOf(idBusinessUnit));

        return exitRepository.findAllDateNotReceivedByBusinessUnit(Long.valueOf(idBusinessUnit));
    }

    @Override
    public List<ExitItemDTO> findExitItemByBusinessUnitAndDateForecast(Integer idBusinessUnit, LocalDate date) {
        publicBusinessUnitService.findPublicBusinessUnitById(Long.valueOf(idBusinessUnit));
        return exitRepository.findExitItemByBusinessUnitAndDateForecast(idBusinessUnit, date);
    }

    @Override
    public List<ExitItemDTO> findExitItemDateForecast(LocalDate date) {
        return exitRepository.findExitItemDateForecast(date);
    }

    @Override
    public void save(InputExitDTO inputExitDTO) {
        Exit exitToSave = buildExit(inputExitDTO);
        exitRepository.save(exitToSave);
    }

    @Override
    public Exit findById(Long idExit) {
        return exitRepository.findById(idExit).orElseThrow(()-> new ExitNotFoundException("exit.not-found"));
    }

    private Exit buildExit(InputExitDTO inputExitDTO) {
        UserUtils userUtils = new UserUtils();
        Item item = itemService.findById(Long.valueOf(inputExitDTO.getIdItem()));
        Employee employee = employeeService.findById(Long.valueOf(inputExitDTO.getIdEmployee()));
        User user = userService.findByUserName(userUtils.getUserDetails().getUsername());

        return Exit.builder()
                .dateExit(LocalDate.now())
                .forecastDate(LocalDate.now().plusDays(item.getDurability()))
                .user(user)
                .employee(employee)
                .item(item)
                .build();
    }
}
