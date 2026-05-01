package br.com.videira.dynamis.kingdomstructure.dto.request;

import br.com.videira.dynamis.kingdomstructure.enums.FuncaoEnum;

public record MembroResquest(
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