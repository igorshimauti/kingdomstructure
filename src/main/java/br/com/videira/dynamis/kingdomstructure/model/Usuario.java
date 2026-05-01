package br.com.videira.dynamis.kingdomstructure.model;

import br.com.videira.dynamis.kingdomstructure.enums.TipoUsuarioEnum;
import br.com.videira.dynamis.kingdomstructure.model.abs.Pessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
@EqualsAndHashCode(callSuper = true)
public class Usuario extends Pessoa implements UserDetails {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo_usuario", length = 20, nullable = false)
    private TipoUsuarioEnum tipoUsuario;

    @NotBlank
    @Column(name = "senha", length = 300, nullable = false, updatable = false)
    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }
}