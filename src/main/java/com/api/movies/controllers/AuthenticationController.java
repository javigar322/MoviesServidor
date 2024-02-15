/**
 * Controlador para la gestión de operaciones de autenticación (registro, inicio de sesión, cierre de sesión).
 * Las operaciones están mapeadas a las rutas /api/v1/auth/signup, /api/v1/auth/signin y /api/v1/auth/logout.
 */
package com.api.movies.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movies.dto.JwtAuthenticationResponse;
import com.api.movies.request.SignUpRequest;
import com.api.movies.request.SigninRequest;
import com.api.movies.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

/**
 * Controlador para las operaciones de autenticación.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    // Inicialización del logger para el controlador
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    // Inyección de dependencias del servicio de autenticación
    @Autowired
    AuthenticationService authenticationService;

    /**
     * Maneja la solicitud de registro de un nuevo usuario.
     * @param request Objeto SignUpRequest que contiene la información del usuario a registrar.
     * @return ResponseEntity con la respuesta de autenticación (token) y detalles del usuario registrado.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        try {
            logger.info("Received signup request: {}", request);
            JwtAuthenticationResponse response = authenticationService.signup(request);
            logger.info("Signup successful for user: {}", request.getFirstName());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error during signup process.", e);
            return new ResponseEntity<>("Error during signup process", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Maneja la solicitud de inicio de sesión de un usuario existente.
     * @param request Objeto SigninRequest que contiene las credenciales del usuario.
     * @return ResponseEntity con la respuesta de autenticación (token) y detalles del usuario autenticado.
     */
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request) {
        try {
            logger.info("Received signin request: {}", request);
            JwtAuthenticationResponse response = authenticationService.signin(request);
            logger.info("Signin successful for user: {}", request.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error during signin process.", e);
            return new ResponseEntity<>("Error during signin process", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Maneja la solicitud de cierre de sesión de un usuario autenticado.
     * @return Mensaje de confirmación de cierre de sesión.
     */
    @PostMapping("/logout")
    public String logout() {
        // Registro de información sobre la solicitud de cierre de sesión
        logger.info("User has logged out");
        // Devuelve un mensaje de confirmación de cierre de sesión al cliente
        return "Has cerrado sesión correctamente";
    }
}
