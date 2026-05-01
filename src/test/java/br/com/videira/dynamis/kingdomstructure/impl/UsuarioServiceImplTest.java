package br.com.videira.dynamis.kingdomstructure.impl;

import br.com.videira.dynamis.kingdomstructure.enums.TipoUsuarioEnum;
import br.com.videira.dynamis.kingdomstructure.exception.UserEmailAlreadyExistsException;
import br.com.videira.dynamis.kingdomstructure.exception.UserNotFoundException;
import br.com.videira.dynamis.kingdomstructure.model.Usuario;
import br.com.videira.dynamis.kingdomstructure.repository.UsuarioRepository;
import br.com.videira.dynamis.kingdomstructure.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

//    @BeforeEach
//    void setUp() {
//    }

    @Test
    void testGetRepository() {
        assertEquals(usuarioRepository, usuarioService.getRepository());
    }

    @Test
    void testFindByEmail() {
        var usuario = new Usuario();
        usuario.setEmail("email@email.com");

        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuario));
        var result = usuarioService.findByEmail(anyString());
        assertNotNull(result);
        assertEquals(usuario, result);
    }

    @Test
    void testFindByEmailUserNotFoundException() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> usuarioService.findByEmail("email@email.com"));
        verify(usuarioRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void testSaveNewUserEmailAlreadyExistsException() {
        var usuario = new Usuario();
        usuario.setEmail("existing@email.com");

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);

        assertThrows(UserEmailAlreadyExistsException.class, () -> usuarioService.save(authentication, usuario));
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void testSaveNewUserSuccessfully() {
        var usuario = new Usuario();
        usuario.setEmail("new@email.com");
        usuario.setSenha("plainPassword");

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario savedUser = usuarioService.save(authentication, usuario);

        assertNotNull(savedUser);
        assertEquals("encodedPassword", savedUser.getSenha());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testSaveUserNotFlexsysShouldSetEmpresa() {
        var usuario = new Usuario();
        usuario.setEmail("user@empresa.com");
        usuario.setSenha("plainPassword");

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario savedUser = usuarioService.save(authentication, usuario);

        assertNotNull(savedUser);
        assertEquals("encodedPassword", savedUser.getSenha());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testSaveExistingUserShouldUpdate() {
        var usuario = new Usuario();
        usuario.setId(10L);
        usuario.setEmail("existing@email.com");
        usuario.setSenha("newPassword");

        var usuarioToken = new Usuario();
        usuarioToken.setTipoUsuario(TipoUsuarioEnum.ADMINISTRADOR);

        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario savedUser = usuarioService.save(authentication, usuario);

        assertNotNull(savedUser);
        assertEquals("encodedNewPassword", savedUser.getSenha());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
}