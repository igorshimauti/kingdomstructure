package br.com.videira.dynamis.kingdomstructure.service;

import br.com.videira.dynamis.kingdomstructure.model.Membro;

public interface MembroService extends CrudService<Membro> {

    Membro findByEmail(String email);
}