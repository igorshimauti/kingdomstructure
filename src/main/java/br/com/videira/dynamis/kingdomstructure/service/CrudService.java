package br.com.videira.dynamis.kingdomstructure.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface CrudService<MODEL> {

    MODEL save(Authentication authentication, MODEL model) throws IllegalAccessException;
    Page<MODEL> findAll(Authentication authentication, Pageable pageable);
    MODEL findById(Long id);
    void inactivateById(Long id);
    boolean existsById(Long id);
}