package ua.clamor1s.task911.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.clamor1s.task911.dao.CardDao;
import ua.clamor1s.task911.dto.*;
import ua.clamor1s.task911.model.Card;

import java.util.List;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDao dao;


    @Override
    public int saveCard(CardSaveDto cardDto) {
        Card card = fromSaveDtoToModel(cardDto);
        dao.saveCard(card);
        return card.getCardId();
    }

    @Transactional(readOnly = true)
    @Override
    public CardDetailsDto getCard(int id) {
        return dao.findCardById(id);
    }

    @Override
    public void updateCard(int id, CardSaveDto cardDto) {
        Card card = fromSaveDtoToModel(cardDto);
        card.setCardId(id);
        dao.updateCard(card);
    }

    @Override
    public void deleteCard(int cardId) {
        dao.deleteCard(cardId);
    }

    @Transactional(readOnly = true)
    @Override
    public CardAllDetailsDto listAll() {
        return dao.listAll();
    }

    @Override
    public CardAllInfoDto search(int page, CardQueryDto query) {
        return dao.search(page, query);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }


    private Card fromSaveDtoToModel(CardSaveDto cardDto) {
        return Card.builder()
                .name(cardDto.getName())
                .surname(cardDto.getSurname())
                .code(cardDto.getCode())
                .cvv(cardDto.getCvv())
                .creationDate(cardDto.getCreationDate())
                .build();
    }

}
