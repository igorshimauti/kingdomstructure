package br.com.videira.dynamis.kingdomstructure.dto.response;

import br.com.videira.dynamis.kingdomstructure.enums.TipoUsuarioEnum;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record UsuarioResponse(
    Long id,
    OffsetDateTime dataCadastro,
    OffsetDateTime ultimaAtualizacao,
    String cpf,
    String email,
    String nomeCompleto,
    LocalDate dataNascimento,
    String telefone,
    Boolean whatsapp,
    TipoUsuarioEnum tipoUsuario
){}