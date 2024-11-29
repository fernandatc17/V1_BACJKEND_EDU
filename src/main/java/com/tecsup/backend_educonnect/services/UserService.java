package com.tecsup.backend_educonnect.services;

import com.tecsup.backend_educonnect.models.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void delete(Long id);
}
