package br.com.videira.dynamis.kingdomstructure.repository;

import br.com.videira.dynamis.kingdomstructure.model.Membro;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembroRepository extends BaseRepository<Membro> {

    Optional<Membro> findByEmail(String email);
    boolean existsByEmail(String email);
}