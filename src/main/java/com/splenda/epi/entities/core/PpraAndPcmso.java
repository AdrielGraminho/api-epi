package com.splenda.epi.entities.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.splenda.epi.entities.enums.PpraAndPcmsoType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "epi_ppra_pcmso", schema = "human_resources_ms")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PpraAndPcmso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_ppra_pcmso", nullable = false)
    private Long idPpraPcmso;

    private LocalDate expirationDate;

    private LocalDate exchangeDate;

    @ManyToOne
    @JoinColumn(name = "business_unit")
    @JsonIgnore
    private PublicBusinessUnit publicBusinessUnit;

    @Enumerated(EnumType.STRING)
    private PpraAndPcmsoType ppraAndPcmsoType;

}
