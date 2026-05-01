package br.com.videira.dynamis.kingdomstructure.mapper.response;

import br.com.videira.dynamis.kingdomstructure.dto.response.UsuarioResponse;
import br.com.videira.dynamis.kingdomstructure.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioResponseMapper extends BaseResponseMapper<UsuarioResponse, Usuario> {

    UsuarioResponse map(Usuario usuario);
}