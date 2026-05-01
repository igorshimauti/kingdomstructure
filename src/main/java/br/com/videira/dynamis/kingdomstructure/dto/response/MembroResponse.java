package br.com.videira.dynamis.kingdomstructure.dto.response;

import br.com.videira.dynamis.kingdomstructure.enums.FuncaoEnum;

import java.time.OffsetDateTime;

public record MembroResponse(
    Long id,
    OffsetDateTime dataCadastro,
    OffsetDateTime ultimaAtualizacao,
    String cpf,
    String email,
    String nomeCompleto,
    String telefone,
    Boolean whatsapp,
    FuncaoEnum funcao,
    Boolean encontro,
    Boolean consolidado,
    Boolean batizado,
    Boolean ceifeiros,
    Boolean maturidade,
    Boolean ctl,
    Boolean seminarioPastoral
){}