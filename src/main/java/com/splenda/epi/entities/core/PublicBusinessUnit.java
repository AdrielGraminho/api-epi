package com.splenda.epi.entities.core;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "business_unit", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicBusinessUnit {
    @Column(name = "id_business_unit")
    @Id
    @EqualsAndHashCode.Include
    private Long idBusinessUnit;

    @Column(name = "id_company")
    private Integer idCompany;

    private Integer code;

    private String description;

    @Column(name = "code_metadados")
    private String codeMetadados;

}
