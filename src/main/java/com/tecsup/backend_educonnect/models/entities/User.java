package com.tecsup.backend_educonnect.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuarios")
    private Long id;

    @Column(name = "Correo_institucional", nullable = false, length = 45)
    private String email;

    @Column(name = "Nombre", nullable = false, length = 45)
    private String firstName;

    @Column(name = "Apellido", nullable = false, length = 45)
    private String lastName;

    @Column(name = "Sexo", length = 45)
    private String gender;

    @Column(name = "Fecha_nacimiento", length = 45)
    private String birthDate;

    @Column(name = "Username", nullable = false, length = 45)
    private String username;

    @Column(name = "Contraseña", nullable = false, length = 45)
    private String password;

    @Column(name = "Ciclo", length = 45)
    private String cycle;



    // Relación con la tabla Eventos (Muchos a Muchos)
    @ManyToMany
    @JoinTable(
            name = "Eventos_has_Usuarios",
            joinColumns = @JoinColumn(name = "Usuarios_idUsuarios"),
            inverseJoinColumns = @JoinColumn(name = "Eventos_idEventos")
    )
    private Set<Evento> eventos;
}
