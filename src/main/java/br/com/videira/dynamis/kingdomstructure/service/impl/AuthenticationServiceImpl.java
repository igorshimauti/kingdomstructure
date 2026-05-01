package br.com.videira.dynamis.kingdomstructure.service.impl;

import br.com.videira.dynamis.kingdomstructure.dto.request.LoginRequest;
import br.com.videira.dynamis.kingdomstructure.model.Usuario;
import br.com.videira.dynamis.kingdomstructure.service.AuthenticationService;
import br.com.videira.dynamis.kingdomstructure.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;

    @Override
    public Usuario authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.senha()
                )
        );

        return usuarioService.findByEmail(loginRequest.email());
    }
}