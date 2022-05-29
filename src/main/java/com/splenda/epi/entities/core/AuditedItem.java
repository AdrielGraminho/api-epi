package com.splenda.epi.entities.core;


import com.splenda.epi.entities.enums.QualifyingFactorType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "epi_audited_item", schema = "human_resources_ms")
@Builder
public class AuditedItem {

    @Id
    @Column(name = "id_audited_item", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idAuditedItem;

    @ManyToOne
    @JoinColumn(name = "auditor_user")
    private User auditorUser;

    @ManyToOne
    @JoinColumn(name = "audit_item")
    private AuditItem auditItem;

    @ManyToOne
    @JoinColumn(name = "business_unit")
    private PublicBusinessUnit publicBusinessUnit;

    @Enumerated(EnumType.STRING)
    private QualifyingFactorType qualifyingFactor;

    private LocalDate auditDate;


}
