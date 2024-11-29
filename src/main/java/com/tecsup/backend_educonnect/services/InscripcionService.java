package com.tecsup.backend_educonnect.services;

import com.tecsup.backend_educonnect.models.entities.Inscripcion;

import java.util.List;

public interface InscripcionService {
    List<Inscripcion> findAll();
    Inscripcion findById(Long id);
    Inscripcion save(Inscripcion inscripcion);
    void delete(Long id);

    List<Inscripcion> findByEventoId(Long eventoId);
}
