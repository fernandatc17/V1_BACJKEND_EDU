package com.tecsup.backend_educonnect.models.daos;

import com.tecsup.backend_educonnect.models.entities.ParticipacionEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipacionRepository extends JpaRepository<ParticipacionEvento, Long> {
    List<ParticipacionEvento> findByEvento_Id(Long eventoId);
}
