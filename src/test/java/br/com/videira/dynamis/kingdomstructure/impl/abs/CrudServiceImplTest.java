package br.com.videira.dynamis.kingdomstructure.impl.abs;

import br.com.videira.dynamis.kingdomstructure.exception.ResourceNotFoundException;
import br.com.videira.dynamis.kingdomstructure.model.Usuario;
import br.com.videira.dynamis.kingdomstructure.model.abs.BaseModel;
import br.com.videira.dynamis.kingdomstructure.repository.BaseRepository;
import br.com.videira.dynamis.kingdomstructure.service.impl.abs.CrudServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrudServiceImplTest {

    private static class FakeModel extends BaseModel {}

    private static class FakeService extends CrudServiceImpl<FakeModel> {
        private final BaseRepository<FakeModel> repository;

        FakeService(BaseRepository<FakeModel> repository) {
            this.repository = repository;
        }

        @Override
        protected BaseRepository<FakeModel> getRepository() {
            return repository;
        }
    }

    @Mock
    private BaseRepository<FakeModel> fakeRepository;

    @Mock
    private Authentication authentication;

    @Mock
    private Usuario usuario;

    @InjectMocks
    private FakeService fakeService;

    @BeforeEach
    void setUp() {
        lenient().when(authentication.getPrincipal()).thenReturn(usuario);
    }

    @Test
    void testSave() {
        FakeModel model = new FakeModel();
        when(fakeRepository.save(model)).thenReturn(model);
        FakeModel savedModel = fakeService.save(authentication, model);
        assertNotNull(savedModel);
        verify(fakeRepository, times(1)).save(model);
    }

    @Test
    void testFindAll() {
        FakeModel model1 = new FakeModel();
        FakeModel model2 = new FakeModel();
        var page = new PageImpl<>(List.of(model1, model2));

        when(fakeRepository.findAll(any(Pageable.class))).thenReturn(page);

        var result = fakeService.findAll(authentication, Pageable.unpaged());

        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());

        verify(fakeRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testFindById_Success() {
        FakeModel model = new FakeModel();
        when(fakeRepository.findById(1L)).thenReturn(Optional.of(model));
        FakeModel result = fakeService.findById(1L);
        assertNotNull(result);
        verify(fakeRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(fakeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> fakeService.findById(1L));
        verify(fakeRepository, times(1)).findById(1L);
    }

    @Test
    void testInactivateById() {
        doNothing().when(fakeRepository).inactiveById(1L);
        fakeService.inactivateById(1L);
        verify(fakeRepository, times(1)).inactiveById(1L);
    }

    @Test
    void testExistsById() {
        when(fakeRepository.existsById(1L)).thenReturn(true);
        assertTrue(fakeService.existsById(1L));
        verify(fakeRepository, times(1)).existsById(1L);
    }
}