package br.com.videira.dynamis.kingdomstructure.dto.request;

public record LoginRequest(
    String email,
    String senha
){}