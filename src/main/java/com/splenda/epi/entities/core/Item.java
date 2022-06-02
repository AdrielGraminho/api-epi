package com.splenda.epi.entities.core;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(schema = "human_resources_ms", name = "epi_item")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_item", nullable = false)
    private Long idItem;

    private String description;
}
