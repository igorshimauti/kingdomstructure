package br.com.videira.dynamis.kingdomstructure.dto.request;

import br.com.videira.dynamis.kingdomstructure.enums.TipoUsuarioEnum;

import java.time.LocalDate;

public record UsuarioRequest(
    String cpf,
    String email,
    String nomeCompleto,
    LocalDate dataNascimento,
    String telefone,
    Boolean whatsapp,
    TipoUsuarioEnum tipoUsuario,
    String senha
){}