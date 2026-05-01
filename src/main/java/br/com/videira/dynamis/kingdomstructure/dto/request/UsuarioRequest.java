package br.com.videira.dynamis.kingdomstructure.dto.request;

import br.com.videira.dynamis.kingdomstructure.enums.TipoUsuarioEnum;

public record UsuarioRequest(
    String cpf,
    String email,
    String nomeCompleto,
    String telefone,
    Boolean whatsapp,
    TipoUsuarioEnum tipoUsuario,
    String senha
){}