package br.com.videira.dynamis.kingdomstructure.model.abs;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Data
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "ativo", nullable = false, updatable = false)
    protected Boolean ativo;

    @CreationTimestamp
    @Column(name = "data_cadastro", updatable = false)
    protected OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "ultima_atualizacao", insertable = false)
    protected OffsetDateTime ultimaAtualizacao;
}