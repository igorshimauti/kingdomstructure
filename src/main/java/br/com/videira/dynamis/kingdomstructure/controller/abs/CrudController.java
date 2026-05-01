package br.com.videira.dynamis.kingdomstructure.controller.abs;

import br.com.videira.dynamis.kingdomstructure.exception.ResourceNotFoundException;
import br.com.videira.dynamis.kingdomstructure.mapper.request.BaseRequestMapper;
import br.com.videira.dynamis.kingdomstructure.mapper.response.BaseResponseMapper;
import br.com.videira.dynamis.kingdomstructure.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public abstract class CrudController<REQUEST, RESPONSE, MODEL> {

    protected abstract CrudService<MODEL> getService();
    protected abstract BaseRequestMapper<REQUEST, MODEL> getRequestMapper();
    protected abstract BaseResponseMapper<RESPONSE, MODEL> getResponseMapper();

    protected abstract void setModelId(MODEL model, Long id);
    protected abstract Long getModelId(MODEL model);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RESPONSE> create(Authentication authentication, @RequestBody REQUEST request) throws IllegalAccessException {
        var model = getRequestMapper().map(request);
        var createdModel = getService().save(authentication, model);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("${api.variables.id}")
                .buildAndExpand(getModelId(model))
                .toUri();

        return ResponseEntity.created(uri).body(getResponseMapper().map(createdModel));
    }

    @GetMapping
    public ResponseEntity<Page<RESPONSE>> findAll(Authentication authentication,
                                             @PageableDefault Pageable pageable) {
        var models = getService().findAll(authentication, pageable).map(getResponseMapper()::map);
        return ResponseEntity.ok().body(models);
    }

    @GetMapping(value = "${api.variables.id}")
    public ResponseEntity<RESPONSE> findById(@PathVariable Long id) {
        var model = getService().findById(id);
        return ResponseEntity.ok().body(getResponseMapper().map(model));
    }

    @PutMapping(value = "${api.variables.id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RESPONSE> update(Authentication authentication, @PathVariable Long id, @RequestBody REQUEST request) throws IllegalAccessException {
        if (!getService().existsById(id)) {
            throw new ResourceNotFoundException();
        }

        var model = getRequestMapper().map(request);
        setModelId(model, id);
        var updatedModel = getService().save(authentication, model);
        return ResponseEntity.ok().body(getResponseMapper().map(updatedModel));
    }

    @DeleteMapping(value = "${api.variables.id}")
    public ResponseEntity<Void> inactivate(@PathVariable Long id) {
        if (!getService().existsById(id)) {
            throw new ResourceNotFoundException();
        }

        getService().inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}