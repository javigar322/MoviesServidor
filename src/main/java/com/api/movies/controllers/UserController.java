/**
 * Controlador para la gestión de operaciones relacionadas con usuarios (obtener todos los usuarios, obtener un usuario por ID, crear usuario).
 * Las operaciones están mapeadas a las rutas /api/v1/user, /api/v1/user/{id}, y /api/v1/user.
 */
package com.api.movies.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movies.dto.UserResponse;
import com.api.movies.entities.User;
import com.api.movies.services.UserService;

/**
 * Controlador para las operaciones relacionadas con usuarios.
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    // Inicialización del logger para el controlador de usuarios
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // Inyección de dependencias del servicio de usuarios
    @Autowired
    UserService userService;

    /**
     * Obtiene la lista de todos los usuarios. Requiere el rol 'ROLE_ADMIN'.
     * @return Lista de respuestas de usuario.
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponse> getAllUser() {
        // Registro de información sobre la solicitud de obtención de todos los usuarios
        logger.info("Getting all users");
        // Llamada al servicio de usuarios para obtener la lista de todos los usuarios
        return userService.getAllUsers();
    }

    /**
     * Obtiene un usuario por su ID. Requiere el rol 'ROLE_ADMIN'.
     * @param id ID del usuario a obtener.
     * @return Respuesta de usuario correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserResponse getUser(@PathVariable Long id) {
        // Registro de información sobre la solicitud de obtención de un usuario por ID
        logger.info("Getting user by ID: {}", id);
        // Llamada al servicio de usuarios para obtener un usuario por ID
        return userService.findUserById(id);
    }

    /**
     * Crea un nuevo usuario. Requiere el rol 'ROLE_ADMIN'.
     * @param user Objeto User que contiene la información del nuevo usuario.
     * @return El usuario creado.
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User createUser(@RequestBody User user) {
        // Registro de información sobre la solicitud de creación de un nuevo usuario
        logger.info("Creating a new user");
        // Llamada al servicio de usuarios para crear un nuevo usuario
        return userService.createUser(user);
    }
}
