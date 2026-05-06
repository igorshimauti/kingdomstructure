package br.com.videira.dynamis.kingdomstructure.integration.rest.dto.response;

public record ViaCepEnderecoResponse(
    String logradouro,
    String complemento,
    String bairro,
    String localidade,
    String uf,
    String estado,
    String regiao,
    String ibge,
    String gia
) {}