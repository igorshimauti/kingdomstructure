package br.com.videira.dynamis.kingdomstructure.impl;

import br.com.videira.dynamis.kingdomstructure.dto.request.LoginRequest;
import br.com.videira.dynamis.kingdomstructure.model.Usuario;
import br.com.videira.dynamis.kingdomstructure.service.UsuarioService;
import br.com.videira.dynamis.kingdomstructure.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    private LoginRequest loginRequest;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest("user@example.com", "password");
        usuario = new Usuario();
        usuario.setEmail("user@example.com");
    }

    @Test
    void authenticate_ShouldReturnUser_WhenCredentialsAreValid() {
        when(usuarioService.findByEmail(loginRequest.email())).thenReturn(usuario);

        Usuario authenticatedUser = authenticationService.authenticate(loginRequest);

        assertNotNull(authenticatedUser);
        assertEquals(loginRequest.email(), authenticatedUser.getEmail());

        verify(authenticationManager, times(1)).authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha())
        );
        verify(usuarioService, times(1)).findByEmail(loginRequest.email());
    }

    @Test
    void authenticate_ShouldThrowException_WhenCredentialsAreInvalid() {
        doThrow(new BadCredentialsException("Invalid credentials"))
                .when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        assertThrows(BadCredentialsException.class, () -> authenticationService.authenticate(loginRequest));

        verify(authenticationManager, times(1)).authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha())
        );
        verify(usuarioService, never()).findByEmail(anyString());
    }

    @Test
    void authenticate_ShouldReturnNull_WhenUserNotFound() {
        when(usuarioService.findByEmail(loginRequest.email())).thenReturn(null);

        Usuario authenticatedUser = authenticationService.authenticate(loginRequest);

        assertNull(authenticatedUser);

        verify(authenticationManager, times(1)).authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha())
        );
        verify(usuarioService, times(1)).findByEmail(loginRequest.email());
    }
}