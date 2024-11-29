package com.tecsup.backend_educonnect.services;

import com.tecsup.backend_educonnect.models.daos.InscripcionRepository;
import com.tecsup.backend_educonnect.models.entities.Inscripcion;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionServiceImpl implements InscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    public List<Inscripcion> findAll() {
        return inscripcionRepository.findAll();
    }

    @Override
    public Inscripcion findById(Long id) {
        return inscripcionRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Inscripcion save(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        inscripcionRepository.deleteById(id);
    }

    // Otros métodos personalizados

    @Override
    public List<Inscripcion> findByEventoId(Long eventoId) {
        return inscripcionRepository.findByEvento_Id(eventoId);
    }
}
