package br.com.videira.dynamis.kingdomstructure.mapper.response;

import java.util.List;

public interface BaseResponseMapper<DTO, MODEL> {

    DTO map(MODEL model);
    List<DTO> map(List<MODEL> models);
}