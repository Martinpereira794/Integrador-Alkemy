package com.bbva.IntegradorJava.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.bbva.IntegradorJava.config.JwtService;
import com.bbva.IntegradorJava.exceptions.ExceptionsTP;
import com.bbva.IntegradorJava.models.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	@Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserServices usuarioService; 

    public Map<String, Object> login( String username,  String password) {
        Map<String, Object> response = new HashMap<>();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User usuario = usuarioService.getByUsername(username);
            response.put("id", usuario.getId());
            response.put("token", jwtService.generateToken(usuario.getId(), username, usuario.getRoles()));
        } catch (Exception e) {
            throw new ExceptionsTP(HttpStatus.UNAUTHORIZED, "Credenciales invalidas...." + e.getMessage());
        }
        return response;
    }

}