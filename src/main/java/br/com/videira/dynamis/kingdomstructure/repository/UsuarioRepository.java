package br.com.videira.dynamis.kingdomstructure.repository;

import br.com.videira.dynamis.kingdomstructure.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario> {

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}