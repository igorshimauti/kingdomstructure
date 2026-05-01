package br.com.videira.dynamis.kingdomstructure.service.impl.abs;


import br.com.videira.dynamis.kingdomstructure.exception.ResourceNotFoundException;
import br.com.videira.dynamis.kingdomstructure.model.abs.BaseModel;
import br.com.videira.dynamis.kingdomstructure.repository.BaseRepository;
import br.com.videira.dynamis.kingdomstructure.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public abstract class CrudServiceImpl<MODEL extends BaseModel> implements CrudService<MODEL> {

    protected abstract BaseRepository<MODEL> getRepository();

    @Transactional
    @Override
    public MODEL save(Authentication authentication, MODEL model) {
        if (model.getId() == null) {
            model.setAtivo(true);
        }

        return getRepository().save(model);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MODEL> findAll(Authentication authentication, Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public MODEL findById(Long id) {
        return getRepository().findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    @Override
    public void inactivateById(Long id) {
        getRepository().inactiveById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return getRepository().existsById(id);
    }
}