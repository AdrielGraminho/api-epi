package com.splenda.epi.dtos;

import com.splenda.epi.entities.enums.QualifyingFactorType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InputAuditedItemDTO {
    private Integer itemId;
    private Integer businessUnitId;
    @Enumerated(EnumType.STRING)
    private QualifyingFactorType qualifyingFactorType;
}
