package com.tecsup.backend_educonnect.services;

import com.tecsup.backend_educonnect.models.entities.ParticipacionEvento;

import java.util.List;

public interface ParticipacionService {
    List<ParticipacionEvento> findAll();
    ParticipacionEvento findById(Long id);
    ParticipacionEvento save(ParticipacionEvento participacion);
    void delete(Long id);

    List<ParticipacionEvento> findByEventoId(Long eventoId);
}
