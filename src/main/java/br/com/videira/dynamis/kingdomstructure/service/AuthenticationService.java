package br.com.videira.dynamis.kingdomstructure.service;

import br.com.videira.dynamis.kingdomstructure.dto.request.LoginRequest;
import br.com.videira.dynamis.kingdomstructure.model.Usuario;

public interface AuthenticationService {

    Usuario authenticate(LoginRequest loginRequest);
}