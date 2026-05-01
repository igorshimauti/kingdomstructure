package br.com.videira.dynamis.kingdomstructure.service.impl;

import br.com.videira.dynamis.kingdomstructure.exception.UserEmailAlreadyExistsException;
import br.com.videira.dynamis.kingdomstructure.exception.UserNotFoundException;
import br.com.videira.dynamis.kingdomstructure.model.Usuario;
import br.com.videira.dynamis.kingdomstructure.repository.BaseRepository;
import br.com.videira.dynamis.kingdomstructure.repository.UsuarioRepository;
import br.com.videira.dynamis.kingdomstructure.service.UsuarioService;
import br.com.videira.dynamis.kingdomstructure.service.impl.abs.CrudServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario> implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseRepository<Usuario> getRepository() {
        return usuarioRepository;
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Usuario save(Authentication authentication, Usuario usuario) {
        if (isNew(usuario)) {
            if (existsByEmail(usuario.getEmail())) {
                throw new UserEmailAlreadyExistsException();
            }

            usuario.setAtivo(true);
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    private boolean isNew(Usuario usuario) {
        return usuario.getId() == null || usuario.getId().equals(0L);
    }

    private boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}