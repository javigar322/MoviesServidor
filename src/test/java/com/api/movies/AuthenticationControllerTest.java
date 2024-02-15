package com.api.movies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.movies.controllers.AuthenticationController;
import com.api.movies.dto.JwtAuthenticationResponse;
import com.api.movies.request.SignUpRequest;
import com.api.movies.request.SigninRequest;
import com.api.movies.services.AuthenticationService;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Test
    void signup_shouldReturnJwtAuthenticationResponse() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        JwtAuthenticationResponse mockResponse = new JwtAuthenticationResponse("token");
        when(authenticationService.signup(signUpRequest)).thenReturn(mockResponse);

        // Act
        ResponseEntity<?> response = authenticationController.signup(signUpRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void signin_shouldReturnJwtAuthenticationResponse() {
        // Arrange
        SigninRequest signinRequest = new SigninRequest();
        JwtAuthenticationResponse mockResponse = new JwtAuthenticationResponse("token");
        when(authenticationService.signin(signinRequest)).thenReturn(mockResponse);

        // Act
        ResponseEntity<?> response = authenticationController.signin(signinRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }

    // Add more tests for other methods in AuthenticationController if needed...
}
