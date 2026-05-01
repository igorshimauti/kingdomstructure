package br.com.videira.dynamis.kingdomstructure.mapper.request;

import br.com.videira.dynamis.kingdomstructure.dto.request.MembroResquest;
import br.com.videira.dynamis.kingdomstructure.model.Membro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MembroRequestMapper extends BaseRequestMapper<MembroResquest, Membro> {

    Membro map(MembroResquest membroResquest);
}