package com.splenda.epi.entities.dtos;

import com.splenda.epi.entities.enums.QualifyingFactorType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InputAuditedItemDTO {
    @NotNull
    private Integer itemId;
    @NotNull
    private Integer businessUnitId;
    @Enumerated(EnumType.STRING)
    @NotNull
    private QualifyingFactorType qualifyingFactorType;
}
