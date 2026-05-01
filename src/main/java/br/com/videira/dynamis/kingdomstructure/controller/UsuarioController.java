package br.com.videira.dynamis.kingdomstructure.controller;

import br.com.videira.dynamis.kingdomstructure.controller.abs.CrudController;
import br.com.videira.dynamis.kingdomstructure.dto.request.UsuarioRequest;
import br.com.videira.dynamis.kingdomstructure.dto.response.UsuarioResponse;
import br.com.videira.dynamis.kingdomstructure.enums.TipoUsuarioEnum;
import br.com.videira.dynamis.kingdomstructure.mapper.request.BaseRequestMapper;
import br.com.videira.dynamis.kingdomstructure.mapper.response.BaseResponseMapper;
import br.com.videira.dynamis.kingdomstructure.mapper.request.UsuarioRequestMapper;
import br.com.videira.dynamis.kingdomstructure.mapper.response.UsuarioResponseMapper;
import br.com.videira.dynamis.kingdomstructure.model.Usuario;
import br.com.videira.dynamis.kingdomstructure.service.CrudService;
import br.com.videira.dynamis.kingdomstructure.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.paths.usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController extends CrudController<UsuarioRequest, UsuarioResponse, Usuario> {

    private final UsuarioService usuarioService;
    private final UsuarioRequestMapper usuarioRequestMapper;
    private final UsuarioResponseMapper usuarioResponseMapper;

    @Override
    protected CrudService<Usuario> getService() {
        return usuarioService;
    }

    @Override
    protected BaseRequestMapper<UsuarioRequest, Usuario> getRequestMapper() {
        return usuarioRequestMapper;
    }

    @Override
    protected BaseResponseMapper<UsuarioResponse, Usuario> getResponseMapper() {
        return usuarioResponseMapper;
    }

    @Override
    protected void setModelId(Usuario usuario, Long id) {
        usuario.setId(id);
    }

    @Override
    protected Long getModelId(Usuario usuario) {
        return usuario.getId();
    }

    @GetMapping("${api.paths.tipo}")
    public ResponseEntity<Map<String, String>> findTipos() {
        var tiposUsuarios = Arrays.stream(TipoUsuarioEnum.values()).collect(Collectors.toMap(Enum::name, TipoUsuarioEnum::getDescription));
        return ResponseEntity.ok().body(tiposUsuarios);
    }
}