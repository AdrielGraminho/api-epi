package com.splenda.epi.entities.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InputExitDTO {
    @NotNull
    private Integer idItem;
    @NotNull
    private Integer idEmployee;
}
