package com.splenda.epi.entities.core;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "human_resources_ms")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "manager_code")
    private Long managerCode;

    private String metadadosCode;

    private String name;

    private String username;

    private String cpf;

    @ManyToOne
    @JoinColumn(name = "business_unit_id")
    private PublicBusinessUnit businessUnit;

    private String regional;

    private LocalDate hireDate;

    private LocalDate leaveDate;

    private String leaveReason;

}
