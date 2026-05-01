package br.com.videira.dynamis.kingdomstructure.impl;

import br.com.videira.dynamis.kingdomstructure.enums.TipoUsuarioEnum;
import br.com.videira.dynamis.kingdomstructure.model.Usuario;
import br.com.videira.dynamis.kingdomstructure.service.impl.JwtServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.SecureRandom;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtServiceImplTest {

    @Mock
    private Usuario usuario;

    @InjectMocks
    private JwtServiceImpl jwtService;

    private String token;

    @BeforeEach
    void setUp() {
        byte[] keyBytes = new byte[64];
        new SecureRandom().nextBytes(keyBytes);
        var fakeSecret = Base64.getEncoder().encodeToString(keyBytes);

        ReflectionTestUtils.setField(jwtService, "secretKey", fakeSecret);
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", 3600000L);

        when(usuario.getUsername()).thenReturn("user@example.com");
        when(usuario.getId()).thenReturn(1L);
        when(usuario.getNomeCompleto()).thenReturn("Usuário Teste");
        when(usuario.getTipoUsuario()).thenReturn(TipoUsuarioEnum.ADMINISTRADOR);

        token = jwtService.generateToken(usuario);
    }

    @Test
    void extractUsername_ShouldReturnCorrectUsername() {
        String username = jwtService.extractUsername(token);
        assertEquals("user@example.com", username);
    }

    @Test
    void generateToken_ShouldCreateValidToken() {
        String generatedToken = jwtService.generateToken(usuario);
        assertNotNull(generatedToken);
        assertEquals(jwtService.extractUsername(generatedToken), usuario.getUsername());
    }

    @Test
    void isTokenValid_ShouldReturnTrueForValidToken() {
        assertTrue(jwtService.isTokenValid(token, usuario));
    }

    @Test
    void getExpirationTime_ShouldReturnConfiguredExpirationTime() {
        assertEquals(3600000, jwtService.getExpirationTime());
    }
}
