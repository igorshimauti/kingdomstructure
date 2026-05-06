package br.com.videira.dynamis.kingdomstructure.service;

import br.com.videira.dynamis.kingdomstructure.integration.rest.dto.response.TrelloCreateCardResponse;

public interface TrelloService {

	TrelloCreateCardResponse createCard(String name, String desc);
	void createCheckList(String cardId, String name);
	void createCheckItem(String checklistId, String name);
}