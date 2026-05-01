package br.com.videira.dynamis.kingdomstructure.service;

import br.com.videira.dynamis.kingdomstructure.model.Usuario;

public interface UsuarioService extends CrudService<Usuario> {

    Usuario findByEmail(String email);
}