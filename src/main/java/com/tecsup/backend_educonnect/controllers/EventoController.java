package com.tecsup.backend_educonnect.controllers;

import com.tecsup.backend_educonnect.models.entities.Evento;
import com.tecsup.backend_educonnect.models.entities.ParticipacionEvento;
import com.tecsup.backend_educonnect.models.entities.User;
import com.tecsup.backend_educonnect.services.EventoService;
import com.tecsup.backend_educonnect.services.InscripcionService;
import com.tecsup.backend_educonnect.services.ParticipacionService;
import com.tecsup.backend_educonnect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000") // Permitir CORS para tu frontend
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ParticipacionService participacionService; // Servicio de participaciones

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private UserService userService;


    // Obtener todos los eventos
    @GetMapping("eventos")
    public List<Evento> listarEventos() {
        return eventoService.findAll();
    }

    // Obtener un evento por ID
    @GetMapping("eventos/{id}")
    public ResponseEntity<Evento> obtenerEvento(@PathVariable Long id) {
        Evento evento = eventoService.findById(id);
        if (evento != null) {
            return new ResponseEntity<>(evento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("eventos")
    @Transactional // Asegura consistencia en caso de error
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento, @RequestParam Long creadorId,
                                              @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        // Buscar al usuario creador
        User creador = userService.findById(creadorId);
        if (creador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        try {
            // Crear y guardar el evento
            Evento eventoCreado = eventoService.crearEvento(evento, imagen);

            // Asociar al creador como participante
            ParticipacionEvento participacion = new ParticipacionEvento();
            participacion.setUsuario(creador);
            participacion.setEvento(eventoCreado);
            participacionService.save(participacion);

            return ResponseEntity.status(HttpStatus.CREATED).body(eventoCreado);

        } catch (IllegalArgumentException e) {
            // Manejo de errores específicos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            // Manejo general de errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    // Actualizar un evento existente
    @PutMapping("eventos/{id}")
    public ResponseEntity<Evento> actualizarEvento(@PathVariable Long id, @RequestBody Evento eventoActualizado,
                                                   @RequestParam(value = "imagen", required = false) MultipartFile imagen) {
        try {
            Evento eventoGuardado = eventoService.actualizarEvento(id, eventoActualizado, imagen);
            return ResponseEntity.ok(eventoGuardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    // Eliminar un evento
    @DeleteMapping("eventos/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long id) {
        if (!eventoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            // Eliminar participaciones asociadas
            participacionService.delete(id);

            // Eliminar inscripciones asociadas
            inscripcionService.delete(id);

            // Eliminar el evento
            eventoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
