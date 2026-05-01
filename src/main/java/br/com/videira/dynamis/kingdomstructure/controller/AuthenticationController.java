package br.com.videira.dynamis.kingdomstructure.controller;

import br.com.videira.dynamis.kingdomstructure.dto.request.LoginRequest;
import br.com.videira.dynamis.kingdomstructure.dto.response.LoginResponse;
import br.com.videira.dynamis.kingdomstructure.service.AuthenticationService;
import br.com.videira.dynamis.kingdomstructure.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.paths.auth}", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "${api.paths.login}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        var authenticatedUser = authenticationService.authenticate(loginRequest);
        var jwtToken = jwtService.generateToken(authenticatedUser);
        var loginResponse = new LoginResponse("Bearer", jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok().body(loginResponse);
    }
}