package com.tecsup.backend_educonnect.models.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ParticipacionEvento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipacionEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idParticipacion")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Usuarios_idUsuarios", referencedColumnName = "idUsuarios")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "Eventos_idEventos", referencedColumnName = "idEventos")
    private Evento evento;
}
