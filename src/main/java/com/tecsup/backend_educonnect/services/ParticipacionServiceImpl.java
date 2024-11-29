package com.tecsup.backend_educonnect.services;

import com.tecsup.backend_educonnect.models.daos.ParticipacionRepository;
import com.tecsup.backend_educonnect.models.entities.ParticipacionEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipacionServiceImpl implements ParticipacionService{
    @Autowired
    private ParticipacionRepository participacionRepository;

    @Override
    public List<ParticipacionEvento> findAll() {
        return participacionRepository.findAll();
    }

    @Override
    public ParticipacionEvento findById(Long id) {
        return participacionRepository.findById(id).orElse(null);
    }

    @Override
    public ParticipacionEvento save(ParticipacionEvento participacion) {
        return participacionRepository.save(participacion);
    }

    @Override
    public void delete(Long id) {
        participacionRepository.deleteById(id);
    }

    // Otros métodos personalizados si es necesario

    @Override
    public List<ParticipacionEvento> findByEventoId(Long eventoId) {
        return participacionRepository.findByEvento_Id(eventoId);
    }
}
