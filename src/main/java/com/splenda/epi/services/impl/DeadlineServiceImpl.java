package com.splenda.epi.services.impl;

import com.splenda.epi.entities.dtos.DeadLineDTO;
import com.splenda.epi.entities.dtos.ExitItemDTO;
import com.splenda.epi.entities.dtos.PpraAndPcmsoDTO;
import com.splenda.epi.entities.exceptions.BusinessUnitIdMustBeProvidedException;
import com.splenda.epi.services.DeadlineService;
import com.splenda.epi.services.ExitService;
import com.splenda.epi.services.PpraAndPcmsoService;
import com.splenda.epi.services.PublicBusinessUnitService;
import com.splenda.epi.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DeadlineServiceImpl implements DeadlineService {

    private final ExitService exitService;
    private final PpraAndPcmsoService ppraAndPcmsoService;
    private final PublicBusinessUnitService publicBusinessUnitService;

    public DeadlineServiceImpl(ExitService exitService, PpraAndPcmsoService ppraAndPcmsoService, PublicBusinessUnitService publicBusinessUnitService) {
        this.exitService = exitService;
        this.ppraAndPcmsoService = ppraAndPcmsoService;
        this.publicBusinessUnitService = publicBusinessUnitService;
    }

    @Override
    public List<DeadLineDTO> findDeadLineByIdBusinessUnitAndDate(Integer idBusinessUnit, LocalDate dateDeadLine) {
        if(Objects.isNull(idBusinessUnit)){
            UserUtils userUtils = new UserUtils();
            if (userUtils.getUserDetails().getAuthorities().toString().contains("TOTAL")){
                return findDeadLineDetailByDate(dateDeadLine);
            }else {
                throw new BusinessUnitIdMustBeProvidedException("business-unit.id-must-be-provided");
            }
        } else {
            return findDeadLineDetailByDateAndBusinessUnit(dateDeadLine, idBusinessUnit);
        }
    }

    private List<DeadLineDTO> findDeadLineDetailByDateAndBusinessUnit(LocalDate dateDeadLine, Integer idBusinessUnit) {
        publicBusinessUnitService.findPublicBusinessUnitById(Long.valueOf(idBusinessUnit));
        List<ExitItemDTO> exitItemDTOList = exitService.findExitItemByBusinessUnitAndDateForecast(idBusinessUnit, dateDeadLine);
        List<PpraAndPcmsoDTO> ppraAndPcmsoDTOList = ppraAndPcmsoService.findByBusinessUnitAndExpirationDate(idBusinessUnit, dateDeadLine);
        return buildDeadLineDto(exitItemDTOList, ppraAndPcmsoDTOList);
    }

    private List<DeadLineDTO> findDeadLineDetailByDate(LocalDate dateDeadLine) {
        List<ExitItemDTO> exitItemDTOList = exitService.findExitItemDateForecast(dateDeadLine);
        List<PpraAndPcmsoDTO> ppraAndPcmsoDTOList = ppraAndPcmsoService.findByExpirationDate(dateDeadLine);
        return buildDeadLineDto(exitItemDTOList, ppraAndPcmsoDTOList);
    }

    private List<DeadLineDTO> buildDeadLineDto(List<ExitItemDTO> exitItemDTOList, List<PpraAndPcmsoDTO> ppraAndPcmsoDTOList) {
        List<DeadLineDTO> deadLineDTOList = new ArrayList<>();

        exitItemDTOList.forEach(exitItemDTO -> {
            DeadLineDTO deadLineDTO = DeadLineDTO
                    .builder()
                    .idDeadLine(exitItemDTO.getIdExit())
                    .description(exitItemDTO.getItem())
                    .businessUnit(exitItemDTO.getBusinessUnit())
                    .build();
            deadLineDTOList.add(deadLineDTO);
        });

        ppraAndPcmsoDTOList.forEach(ppraAndPcmsoDTO -> {
            DeadLineDTO deadLineDTO = DeadLineDTO
                    .builder()
                    .idDeadLine(ppraAndPcmsoDTO.getIdPpraAndPcmso())
                    .description(ppraAndPcmsoDTO.getDescription())
                    .businessUnit(ppraAndPcmsoDTO.getBusinessUnit())
                    .build();
        deadLineDTOList.add(deadLineDTO);
        });

        return deadLineDTOList;
    }
}
