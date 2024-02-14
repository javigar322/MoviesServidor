package com.api.movies.services;

import com.api.movies.dto.JwtAuthenticationResponse;
import com.api.movies.request.SignUpRequest;
import com.api.movies.request.SigninRequest;

public interface AuthenticationService {
	
	/** REGISTRO */
    JwtAuthenticationResponse signup(SignUpRequest request);
    /** ACCESO a Token JWT */
    JwtAuthenticationResponse signin(SigninRequest request);
}
