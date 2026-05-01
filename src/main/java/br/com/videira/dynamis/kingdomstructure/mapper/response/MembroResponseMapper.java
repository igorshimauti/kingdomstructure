package br.com.videira.dynamis.kingdomstructure.mapper.response;

import br.com.videira.dynamis.kingdomstructure.dto.response.MembroResponse;
import br.com.videira.dynamis.kingdomstructure.model.Membro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MembroResponseMapper extends BaseResponseMapper<MembroResponse, Membro> {

    MembroResponse map(Membro membro);
}