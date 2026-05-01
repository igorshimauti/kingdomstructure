package br.com.videira.dynamis.kingdomstructure.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    long getExpirationTime();
    boolean isTokenValid(String token, UserDetails userDetails);
}