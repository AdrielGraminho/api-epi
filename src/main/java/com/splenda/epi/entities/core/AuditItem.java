package com.splenda.epi.entities.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "human_resources_ms", name = "epi_audit_item")
public class AuditItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_audit_item", nullable = false)
    private Long idAuditItem;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_audit_type")
    @EqualsAndHashCode.Include
    @JsonIgnore
    private AuditType auditType;
}
