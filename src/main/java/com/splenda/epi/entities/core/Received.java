package com.splenda.epi.entities.core;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "human_resources_ms", name = "epi_received")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Received {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_received", nullable = false)
    private Long idReceived;

    @ManyToOne
    @JoinColumn(name = "id_exit")
    private Exit exit;

    private LocalDate dateReceived;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
