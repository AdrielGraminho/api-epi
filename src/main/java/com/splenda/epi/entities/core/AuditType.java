package com.splenda.epi.entities.core;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "human_resources_ms", name = "epi_audit_type")
public class AuditType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_audit_type", nullable = false)
    private Long idAuditType;

    private String description;

    @OneToMany(mappedBy = "auditType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuditItem> auditItemList;

}
