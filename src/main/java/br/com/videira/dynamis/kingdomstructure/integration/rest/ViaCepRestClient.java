package br.com.videira.dynamis.kingdomstructure.integration.rest;

import br.com.videira.dynamis.kingdomstructure.integration.rest.dto.response.ViaCepEnderecoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep")
public interface ViaCepRestClient {

    @GetMapping("/ws/{cep}/json")
    ViaCepEnderecoResponse getAddress(@PathVariable("cep") String cep);
}