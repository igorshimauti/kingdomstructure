package br.com.videira.dynamis.kingdomstructure.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FuncaoEnum {

    PASTOR_GOVERNO("Pastor de governo"),
    PASTOR_AUXILIAR("Pastor auxiliar"),
    OBREIRO("Obreiro"),
    DISCIPULADOR("Discipulador"),
    LIDER("Líder de célula"),
    LT("Líder em treinamento"),
    CONSOLIDADOR("Consolidador"),
    SEM_FUNCAO("Sem função");

    private final String description;
}