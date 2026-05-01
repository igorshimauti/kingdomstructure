package br.com.videira.dynamis.kingdomstructure.impl;

import br.com.videira.dynamis.kingdomstructure.exception.UserEmailAlreadyExistsException;
import br.com.videira.dynamis.kingdomstructure.exception.UserNotFoundException;
import br.com.videira.dynamis.kingdomstructure.model.Membro;
import br.com.videira.dynamis.kingdomstructure.repository.MembroRepository;
import br.com.videira.dynamis.kingdomstructure.service.impl.MembroServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

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
class MembroServiceImplTest {

    @Mock
    private MembroRepository membroRepository;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private MembroServiceImpl membroService;

//    @BeforeEach
//    void setUp() {
//    }

    @Test
    void testGetRepository() {
        assertEquals(membroRepository, membroService.getRepository());
    }

    @Test
    void testFindByEmail() {
        var membro = new Membro();
        membro.setEmail("email@email.com");

        when(membroRepository.findByEmail(anyString())).thenReturn(Optional.of(membro));
        var result = membroService.findByEmail(anyString());
        assertNotNull(result);
        assertEquals(membro, result);
    }

    @Test
    void testFindByEmailUserNotFoundException() {
        when(membroRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> membroService.findByEmail("email@email.com"));
        verify(membroRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void testSaveNewUserEmailAlreadyExistsException() {
        var membro = new Membro();
        membro.setEmail("existing@email.com");

        when(membroRepository.existsByEmail(membro.getEmail())).thenReturn(true);

        assertThrows(UserEmailAlreadyExistsException.class, () -> membroService.save(authentication, membro));
        verify(membroRepository, never()).save(any());
    }

    @Test
    void testSaveNewUserSuccessfully() {
        var membro = new Membro();
        membro.setEmail("new@email.com");

        when(membroRepository.existsByEmail(membro.getEmail())).thenReturn(false);
        when(membroRepository.save(any(Membro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var membroSalvo = membroService.save(authentication, membro);

        assertNotNull(membroSalvo);
        verify(membroRepository, times(1)).save(membro);
    }

    @Test
    void testSaveUserNotFlexsysShouldSetEmpresa() {
        var membro = new Membro();
        membro.setEmail("user@empresa.com");

        when(membroRepository.existsByEmail(membro.getEmail())).thenReturn(false);
        when(membroRepository.save(any(Membro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var membroSalvo = membroService.save(authentication, membro);

        assertNotNull(membroSalvo);
        verify(membroRepository, times(1)).save(membro);
    }

    @Test
    void testSaveExistingUserShouldUpdate() {
        var membro = new Membro();
        membro.setId(10L);
        membro.setEmail("existing@email.com");

        when(membroRepository.save(any(Membro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var membroSalvo = membroService.save(authentication, membro);

        assertNotNull(membroSalvo);
        verify(membroRepository, times(1)).save(any(Membro.class));
    }
}