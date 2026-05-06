package br.com.videira.dynamis.kingdomstructure.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoUsuarioEnum {

    ADMINISTRADOR("Administrador"),
    COMUM("Comum");

    private final String description;
}