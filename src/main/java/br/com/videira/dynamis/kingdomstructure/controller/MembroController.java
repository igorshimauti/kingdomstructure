package br.com.videira.dynamis.kingdomstructure.controller;

import br.com.videira.dynamis.kingdomstructure.controller.abs.CrudController;
import br.com.videira.dynamis.kingdomstructure.dto.request.MembroResquest;
import br.com.videira.dynamis.kingdomstructure.dto.response.MembroResponse;
import br.com.videira.dynamis.kingdomstructure.enums.FuncaoEnum;
import br.com.videira.dynamis.kingdomstructure.mapper.request.BaseRequestMapper;
import br.com.videira.dynamis.kingdomstructure.mapper.response.BaseResponseMapper;
import br.com.videira.dynamis.kingdomstructure.mapper.request.MembroRequestMapper;
import br.com.videira.dynamis.kingdomstructure.mapper.response.MembroResponseMapper;
import br.com.videira.dynamis.kingdomstructure.model.Membro;
import br.com.videira.dynamis.kingdomstructure.service.CrudService;
import br.com.videira.dynamis.kingdomstructure.service.MembroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.paths.membro}", produces = MediaType.APPLICATION_JSON_VALUE)
public class MembroController extends CrudController<MembroResquest, MembroResponse, Membro> {

    private final MembroService membroService;
    private final MembroRequestMapper membroRequestMapper;
    private final MembroResponseMapper membroResponseMapper;

    @Override
    protected CrudService<Membro> getService() {
        return membroService;
    }

    @Override
    protected BaseRequestMapper<MembroResquest, Membro> getRequestMapper() {
        return membroRequestMapper;
    }

    @Override
    protected BaseResponseMapper<MembroResponse, Membro> getResponseMapper() {
        return membroResponseMapper;
    }

    @Override
    protected void setModelId(Membro membro, Long id) {
        membro.setId(id);
    }

    @Override
    protected Long getModelId(Membro membro) {
        return membro.getId();
    }

    @GetMapping("${api.paths.funcao}")
    public ResponseEntity<Map<String, String>> findFuncoes() {
        var funcoes = Arrays.stream(FuncaoEnum.values())
                .collect(Collectors.toMap(
                        Enum::name,
                        FuncaoEnum::getDescription,
                        (existing, ignored) -> existing,
                        LinkedHashMap::new
                ));
        return ResponseEntity.ok().body(funcoes);
    }
}