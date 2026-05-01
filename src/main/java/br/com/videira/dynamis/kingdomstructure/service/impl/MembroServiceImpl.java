package br.com.videira.dynamis.kingdomstructure.service.impl;

import br.com.videira.dynamis.kingdomstructure.exception.UserEmailAlreadyExistsException;
import br.com.videira.dynamis.kingdomstructure.exception.UserNotFoundException;
import br.com.videira.dynamis.kingdomstructure.model.Membro;
import br.com.videira.dynamis.kingdomstructure.repository.BaseRepository;
import br.com.videira.dynamis.kingdomstructure.repository.MembroRepository;
import br.com.videira.dynamis.kingdomstructure.service.MembroService;
import br.com.videira.dynamis.kingdomstructure.service.impl.abs.CrudServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembroServiceImpl extends CrudServiceImpl<Membro> implements MembroService {

    private final MembroRepository membroRepository;

    @Override
    public BaseRepository<Membro> getRepository() {
        return membroRepository;
    }

    @Override
    public Membro findByEmail(String email) {
        return membroRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Membro save(Authentication authentication, Membro membro) {
        if (isNew(membro)) {
            if (existsByEmail(membro.getEmail())) {
                throw new UserEmailAlreadyExistsException();
            }

            membro.setAtivo(true);
        }

        return membroRepository.save(membro);
    }

    private boolean isNew(Membro membro) {
        return membro.getId() == null || membro.getId().equals(0L);
    }

    private boolean existsByEmail(String email) {
        return membroRepository.existsByEmail(email);
    }
}