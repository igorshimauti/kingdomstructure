package br.com.videira.dynamis.kingdomstructure.dto.response;

public record LoginResponse(
    String type,
    String token,
    long expiresIn
){}