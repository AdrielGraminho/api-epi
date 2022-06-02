package com.splenda.epi.entities.core;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "human_resources_ms", name = "epi_exit")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_exit", nullable = false)
    private Long idExit;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    @EqualsAndHashCode.Include
    private Employee employee;

    private LocalDate dateExit;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @EqualsAndHashCode.Include
    private User user;

    private LocalDate forecastDate;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;
}
