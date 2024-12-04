package com.fariasvision.TaskManager.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;
    private String deadline;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private Usuario usuario;
}
