package br.com.videira.dynamis.kingdomstructure.integration.rest;

import br.com.videira.dynamis.kingdomstructure.integration.rest.dto.response.TrelloCreateCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "trello")
public interface TrelloRestClient {

    @PostMapping("/1/cards")
    TrelloCreateCardResponse createCard(
            @RequestParam("idList") String listId,
            @RequestParam("key") String apiKey,
            @RequestParam("token") String apiToken,
            @RequestParam("name") String name,
            @RequestParam("desc") String description
    );

    @PostMapping("1/checklists")
    void createCheckList(
            @RequestParam("idCard") String cardId,
            @RequestParam("key") String apiKey,
            @RequestParam("token") String apiToken,
            @RequestParam("name") String name
    );

    @PostMapping("/1/checklists/{id}/checkItems")
    void createCheckItem(
            @PathVariable("id") String checklistId,
            @RequestParam("key") String apiKey,
            @RequestParam("token") String apiToken,
            @RequestParam("name") String name
    );
}