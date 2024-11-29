package com.tecsup.backend_educonnect.services;

import com.tecsup.backend_educonnect.models.entities.Evento;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventoService {
    List<Evento> findAll();
    Evento findById(Long id);
    Evento crearEvento(Evento evento, MultipartFile imagen);
    Evento actualizarEvento(Long id ,Evento evento, MultipartFile imagen);
    void delete(Long id);
    boolean existsById(Long id);

}
