package br.com.videira.dynamis.kingdomstructure.model.abs;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@MappedSuperclass
public abstract class Pessoa extends BaseModel {

    @CPF
    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Email
    @Size(max = 200)
    @Column(name = "email", length = 200, nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(max = 200)
    @Column(name = "nome_completo", length = 200, nullable = false)
    private String nomeCompleto;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @NotBlank
    @Size(max = 11)
    @Column(name = "telefone", length = 11)
    private String telefone;

    @Column(name = "whatsapp")
    private Boolean whatsapp;
}