package br.com.videira.dynamis.kingdomstructure.service.impl;

import br.com.videira.dynamis.kingdomstructure.integration.rest.TrelloRestClient;
import br.com.videira.dynamis.kingdomstructure.integration.rest.dto.response.TrelloCreateCardResponse;
import br.com.videira.dynamis.kingdomstructure.service.TrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrelloServiceImpl implements TrelloService {

    private final TrelloRestClient trelloRestClient;

    private static final String LIST_ID = "69f6bee95c0568d2648f82c3";
    private static final String API_KEY = "79f895136d03fc7feb3218ad2d5ae7c0";
    private static final String TOKEN = "ATTA0c74b93dc608f15f656c286a7d9fa2667209dbe446103db0a463a52aa9f9e246D843EB66";

    @Override
    public TrelloCreateCardResponse createCard(String name, String desc) {
        return trelloRestClient.createCard(LIST_ID, API_KEY, TOKEN, name, desc);
    }

    @Override
    public void createCheckList(String cardId, String name) {
        trelloRestClient.createCheckList(cardId, API_KEY, TOKEN, name);
    }

    @Override
    public void createCheckItem(String checklistId, String name) {
        trelloRestClient.createCheckItem(checklistId, API_KEY, TOKEN, name);
    }
}