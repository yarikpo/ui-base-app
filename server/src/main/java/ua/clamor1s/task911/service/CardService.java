package ua.clamor1s.task911.service;

import ua.clamor1s.task911.dto.*;
import ua.clamor1s.task911.model.Card;

import java.util.List;

public interface CardService {

    int saveCard(CardSaveDto card);

    CardDetailsDto getCard(int id);

    void updateCard(int id, CardSaveDto card);

    void deleteCard(int cardId);

    CardAllDetailsDto listAll();

    CardAllInfoDto search(int page, CardQueryDto query);

    void deleteAll();

}
