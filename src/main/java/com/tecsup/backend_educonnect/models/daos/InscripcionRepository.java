package com.tecsup.backend_educonnect.models.daos;

import com.tecsup.backend_educonnect.models.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByEvento_Id(Long eventoId);
}

