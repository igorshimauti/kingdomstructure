package br.com.videira.dynamis.kingdomstructure.model;

import br.com.videira.dynamis.kingdomstructure.enums.FuncaoEnum;
import br.com.videira.dynamis.kingdomstructure.model.abs.Pessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "membro")
@EqualsAndHashCode(callSuper = true)
public class Membro extends Pessoa {

    @NotNull
    @Column(name = "encontro", nullable = false)
    private Boolean encontro;

    @NotNull
    @Column(name = "consolidado", nullable = false)
    private Boolean consolidado;

    @NotNull
    @Column(name = "batizado", nullable = false)
    private Boolean batizado;

    @NotNull
    @Column(name = "ceifeiros", nullable = false)
    private Boolean ceifeiros;

    @NotNull
    @Column(name = "maturidade", nullable = false)
    private Boolean maturidade;

    @NotNull
    @Column(name = "ctl", nullable = false)
    private Boolean ctl;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "funcao", length = 20, nullable = false)
    private FuncaoEnum funcao;

    @NotNull
    @Column(name = "seminario_pastoral", nullable = false)
    private Boolean seminarioPastoral;
}