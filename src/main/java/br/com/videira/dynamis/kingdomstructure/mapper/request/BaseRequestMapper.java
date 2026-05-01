package br.com.videira.dynamis.kingdomstructure.mapper.request;

import java.util.List;

public interface BaseRequestMapper<DTO, MODEL> {

    MODEL map(DTO dto);
    List<MODEL> map(List<DTO> dtos);
}