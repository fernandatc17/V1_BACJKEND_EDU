package com.tecsup.backend_educonnect.models.daos;

import com.tecsup.backend_educonnect.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
