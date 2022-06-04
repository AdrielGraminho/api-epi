package com.splenda.epi.entities.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemEmployeeDTO {
    private Long idItem;
    private String description;
    private Integer durability;
    private Long idExit;
}
