package com.upc.ep.security.controllers;

import com.upc.ep.security.dtos.AuthRequestDTO;
import com.upc.ep.security.dtos.AuthResponseDTO;
import com.upc.ep.security.entities.User; // --- AÑADIDO ---
import com.upc.ep.security.repositories.UserRepository; // --- AÑADIDO ---
import com.upc.ep.security.services.CustomUserDetailsService;
import com.upc.ep.security.util.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "${ip.frontend}", allowCredentials = "true", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/CrediHome")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository; // --- AÑADIDO ---

    // --- CORREGIDO: Se añade UserRepository al constructor ---
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailsService userDetailsService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository; // --- AÑADIDO ---
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> createAuthenticationToken(@RequestBody AuthRequestDTO authRequest) throws Exception {
        // Autenticación (esto no cambia)
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        // --- AÑADIDO: Buscar el objeto User completo desde la base de datos ---
        final User user = userRepository.findByUsername(authRequest.getUsername())
            .orElseThrow(() -> new Exception("Error al obtener datos del usuario para el token"));

        // --- CORREGIDO: Se pasa el objeto 'user' completo para generar el token ---
        final String token = jwtUtil.generateToken(userDetails, user);

        Set<String> roles = userDetails.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toSet());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setRoles(roles);
        authResponseDTO.setJwt(token);

        return ResponseEntity.ok().headers(responseHeaders).body(authResponseDTO);
    }
}