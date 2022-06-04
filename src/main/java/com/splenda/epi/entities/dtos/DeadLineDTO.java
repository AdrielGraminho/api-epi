package com.splenda.epi.entities.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeadLineDTO {
    private Integer idDeadLine;
    private String description;
}
