package com.tecsup.backend_educonnect.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Inscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInscripcion")
    private Long id;

    @Column(name = "fecha_inscripcion")
    private Date fechaInscripcion;

    @Column(name = "estado_inscripcion")
    private Boolean estadoInscripcion;

    @ManyToOne
    @JoinColumn(name = "Usuarios_idUsuarios", referencedColumnName = "idUsuarios")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "Eventos_idEventos", referencedColumnName = "idEventos")
    private Evento evento;


}
