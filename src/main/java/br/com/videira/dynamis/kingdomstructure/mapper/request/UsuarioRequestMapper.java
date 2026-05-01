package br.com.videira.dynamis.kingdomstructure.mapper.request;

import br.com.videira.dynamis.kingdomstructure.dto.request.UsuarioRequest;
import br.com.videira.dynamis.kingdomstructure.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioRequestMapper extends BaseRequestMapper<UsuarioRequest, Usuario> {

    Usuario map(UsuarioRequest usuarioRequest);
}