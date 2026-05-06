package br.com.videira.dynamis.kingdomstructure.controller;

import br.com.videira.dynamis.kingdomstructure.controller.abs.CrudController;
import br.com.videira.dynamis.kingdomstructure.dto.request.UsuarioRequest;
import br.com.videira.dynamis.kingdomstructure.dto.response.UsuarioResponse;
import br.com.videira.dynamis.kingdomstructure.enums.TipoUsuarioEnum;
import br.com.videira.dynamis.kingdomstructure.mapper.request.BaseRequestMapper;
import br.com.videira.dynamis.kingdomstructure.mapper.request.UsuarioRequestMapper;
import br.com.videira.dynamis.kingdomstructure.mapper.response.BaseResponseMapper;
import br.com.videira.dynamis.kingdomstructure.mapper.response.UsuarioResponseMapper;
import br.com.videira.dynamis.kingdomstructure.model.Usuario;
import br.com.videira.dynamis.kingdomstructure.service.CrudService;
import br.com.videira.dynamis.kingdomstructure.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
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

    @Override
    public ResponseEntity<UsuarioResponse> create(Authentication authentication, UsuarioRequest usuarioRequest) throws IllegalAccessException {
        return super.create(authentication, usuarioRequest);
    }

    @Override
    public ResponseEntity<Page<UsuarioResponse>> findAll(Authentication authentication, Pageable pageable) {
        return super.findAll(authentication, pageable);
    }

    @Override
    public ResponseEntity<UsuarioResponse> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public ResponseEntity<UsuarioResponse> update(Authentication authentication, Long id, UsuarioRequest usuarioRequest) throws IllegalAccessException {
        return super.update(authentication, id, usuarioRequest);
    }

    @Override
    public ResponseEntity<Void> inactivate(Long id) {
        return super.inactivate(id);
    }

    @GetMapping("${api.paths.tipo}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, String>> findTipos() {
        var tiposUsuarios = Arrays.stream(TipoUsuarioEnum.values()).collect(Collectors.toMap(Enum::name, TipoUsuarioEnum::getDescription));
        return ResponseEntity.ok().body(tiposUsuarios);
    }
}