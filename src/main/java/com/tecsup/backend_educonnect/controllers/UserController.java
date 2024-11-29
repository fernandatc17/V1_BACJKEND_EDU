package com.tecsup.backend_educonnect.controllers;

import com.tecsup.backend_educonnect.models.entities.User;
import com.tecsup.backend_educonnect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:3000") // Permitir CORS para tu frontend
public class UserController {

    @Autowired
    private UserService userService;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> listarUsuarios() {
        List<User> usuarios = userService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public User obtenerUsuario(@PathVariable Long id) {
        return userService.findById(id);
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<User> crearUsuario(@RequestBody User nuevoUsuario) {
        if (nuevoUsuario.getEmail() == null || nuevoUsuario.getUsername() == null) {
            return ResponseEntity.badRequest().build(); // Validación simple
        }
        User usuarioCreado = userService.save(nuevoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    // Actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<User> actualizarUsuario(@PathVariable Long id, @RequestBody User usuarioActualizado) {
        User usuarioExistente = userService.findById(id);

        if (usuarioExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve 404 si no se encuentra el usuario
        }

        // Actualizar los campos
        usuarioExistente.setFirstName(usuarioActualizado.getFirstName());
        usuarioExistente.setLastName(usuarioActualizado.getLastName());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setGender(usuarioActualizado.getGender());
        usuarioExistente.setBirthDate(usuarioActualizado.getBirthDate());
        usuarioExistente.setUsername(usuarioActualizado.getUsername());
        usuarioExistente.setPassword(usuarioActualizado.getPassword());
        usuarioExistente.setCycle(usuarioActualizado.getCycle());

        User usuarioGuardado = userService.save(usuarioExistente);
        return ResponseEntity.ok(usuarioGuardado);
    }


    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        userService.delete(id);
    }
}
