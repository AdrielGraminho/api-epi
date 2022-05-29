package com.splenda.epi.services.impl;

import com.splenda.epi.dtos.InputAuditedItemDTO;
import com.splenda.epi.entities.core.AuditItem;
import com.splenda.epi.entities.core.AuditedItem;
import com.splenda.epi.entities.core.PublicBusinessUnit;
import com.splenda.epi.entities.core.User;
import com.splenda.epi.entities.enums.QualifyingFactorType;
import com.splenda.epi.repository.AuditedItemRepository;
import com.splenda.epi.repository.PublicBusinessUnitRepository;
import com.splenda.epi.services.AuditService;
import com.splenda.epi.services.AuditedItemService;
import com.splenda.epi.services.PublicBusinessUnitService;
import com.splenda.epi.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuditedItemServiceImpl implements AuditedItemService {
    private final AuditedItemRepository auditedItemRepository;
    private final AuditService auditService;
    private final UserService userService;

    private final PublicBusinessUnitService publicBusinessUnitService;


    public AuditedItemServiceImpl(AuditedItemRepository auditedItemRepository, AuditService auditService, UserService userService, PublicBusinessUnitRepository publicBusinessUnitRepository, PublicBusinessUnitService publicBusinessUnitService) {
        this.auditedItemRepository = auditedItemRepository;
        this.auditService = auditService;
        this.userService = userService;
        this.publicBusinessUnitService = publicBusinessUnitService;
    }


    @Override
    public void save(InputAuditedItemDTO inputAuditedItemdto) {

        UserDetails userDetails = userService.getUserDetailsLogged();
        User user = userService.findByUserName(userDetails.getUsername());
        AuditItem auditItem = auditService.findAuditItemById(Long.valueOf(inputAuditedItemdto.getItemId()));
        PublicBusinessUnit publicBusinessUnit = publicBusinessUnitService.findPublicBusinessUnitById(Long.valueOf(inputAuditedItemdto.getBusinessUnitId()));
        AuditedItem auditedItemToSave = buildAuditedItemByUserAndAuditItem(user, auditItem, publicBusinessUnit, inputAuditedItemdto.getQualifyingFactorType());
        auditedItemToSave = setAuditedItemIfThisExists(inputAuditedItemdto, auditedItemToSave);

        auditedItemRepository.save(auditedItemToSave);

    }

    private AuditedItem setAuditedItemIfThisExists(InputAuditedItemDTO inputAuditedItemdto, AuditedItem auditedItemToSave) {
        Optional<AuditedItem> auditedItem =  auditedItemRepository.findByAuditItemBusinessUnitAndDate(Long.valueOf(inputAuditedItemdto.getItemId()), Long.valueOf(inputAuditedItemdto.getBusinessUnitId()));
        if (auditedItem.isPresent()){
            auditedItemToSave.setIdAuditedItem(auditedItem.get().getIdAuditedItem());
        }
        return auditedItemToSave;
    }

    private AuditedItem buildAuditedItemByUserAndAuditItem(User user, AuditItem auditItem, PublicBusinessUnit publicBusinessUnit, QualifyingFactorType qualifyingFactorType) {
        return AuditedItem.builder()
                .auditDate(LocalDate.now())
                .auditorUser(user)
                .auditItem(auditItem)
                .publicBusinessUnit(publicBusinessUnit)
                .qualifyingFactor(qualifyingFactorType)
                .build();
    }


}
