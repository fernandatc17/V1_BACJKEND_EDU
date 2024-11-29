package com.tecsup.backend_educonnect.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tecsup.backend_educonnect.models.daos.EventoRepository;
import com.tecsup.backend_educonnect.models.entities.Evento;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class EventoServiceImpl implements EventoService{

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento findById(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Evento crearEvento(Evento evento, MultipartFile imagen) {
        // Guardar la imagen si está presente
        if (imagen != null && !imagen.isEmpty()) {
            String imagenUrl = guardarImagenEnCloudinary(imagen);
            evento.setImagenUrl(imagenUrl);
        }

        // Guardar el evento en el repositorio
        return eventoRepository.save(evento);
    }

    @Transactional
    @Override
    public Evento actualizarEvento(Long id, Evento evento, MultipartFile imagen) {
        // Buscar el evento existente
        Evento existente = eventoRepository.findById(id).orElse(null);
        if (existente == null) {
            throw new IllegalArgumentException("Evento no encontrado");
        }

        // Actualizar los datos del evento
        existente.setTitulo(evento.getTitulo());
        existente.setDescripcion(evento.getDescripcion());
        existente.setFechaEvento(evento.getFechaEvento());
        existente.setHoraEvento(evento.getHoraEvento());
        existente.setUbicacion(evento.getUbicacion());
        existente.setCapacidad(evento.getCapacidad());
        existente.setCategoria(evento.getCategoria());
        existente.setDuracionEstimada(evento.getDuracionEstimada());

        // Actualizar la imagen si está presente
        if (imagen != null && !imagen.isEmpty()) {
            String imagenUrl = guardarImagenEnCloudinary(imagen);
            existente.setImagenUrl(imagenUrl);
        }

        return eventoRepository.save(existente);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new IllegalArgumentException("El evento no existe");
        }
        eventoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return eventoRepository.existsById(id);
    }

    private String guardarImagenEnCloudinary(MultipartFile imagen) {
        try {
            Map uploadResult = cloudinary.uploader().upload(imagen.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
