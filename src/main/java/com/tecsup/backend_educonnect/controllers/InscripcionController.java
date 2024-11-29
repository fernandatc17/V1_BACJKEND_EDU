package com.tecsup.backend_educonnect.controllers;

import com.tecsup.backend_educonnect.models.daos.EventoRepository;
import com.tecsup.backend_educonnect.models.daos.UserRepository;
import com.tecsup.backend_educonnect.models.entities.Evento;
import com.tecsup.backend_educonnect.models.entities.Inscripcion;
import com.tecsup.backend_educonnect.models.entities.User;
import com.tecsup.backend_educonnect.models.request.InscripcionRequest;
import com.tecsup.backend_educonnect.services.InscripcionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "http://localhost:3000") // Permitir CORS para tu frontend
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private EventoRepository eventoRepository; // Agrega el repositorio de Evento

    @Autowired
    private UserRepository userRepository; // Agrega el repositorio de User


    // Obtener todas las inscripciones
    @GetMapping
    public List<Inscripcion> listarInscripciones() {
        return inscripcionService.findAll();
    }

    // Obtener una inscripción por ID
    @GetMapping("/{id}")
    public Inscripcion obtenerInscripcion(@PathVariable Long id) {
        return inscripcionService.findById(id);
    }


    @PostMapping
    @Transactional
    public Inscripcion registrarInscripcion(@RequestBody InscripcionRequest request) {
        Evento evento = eventoRepository.findById(request.getEventoId())
                .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));

        User usuario = userRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEvento(evento);
        inscripcion.setUsuario(usuario);
        inscripcion.setFechaInscripcion(new Date());
        inscripcion.setEstadoInscripcion(request.getEstadoInscripcion());

        return inscripcionService.save(inscripcion);
    }



    // Desinscribir a un usuario de un evento
    @DeleteMapping("/{id}")
    public void eliminarInscripcion(@PathVariable Long id) {
        inscripcionService.delete(id);
    }
}
