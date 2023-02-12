package ua.clamor1s.task911.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.clamor1s.task911.dto.*;
import ua.clamor1s.task911.model.Card;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CardDao {

    @Autowired
    private EntityManager entityManager;

    public CardDetailsDto findCardById(int id) {
        Card card = entityManager.find(Card.class, id);
        return CardDetailsDto.builder()
                .cardId(card.getCardId())
                .name(card.getName())
                .surname(card.getSurname())
                .code(card.getCode())
                .cvv(card.getCvv())
                .creationDate(card.getCreationDate())
                .build();
    }

    public Card findCard(int id) {
        return entityManager.find(Card.class, id);
    }


    public CardAllDetailsDto listAll() {
        return new CardAllDetailsDto(listCard2ListCardDetails(entityManager.createQuery("SELECT c from Card c", Card.class).getResultList()));
    }

    public void saveCard(Card card) {
        entityManager.persist(card);
    }

    public void updateCard(Card card) {
        entityManager.merge(card);
    }

    public void deleteCard(int cardId) {
        int isSuccess = entityManager.createQuery("DELETE FROM Card c WHERE c.cardId=:id")
                .setParameter("id", cardId)
                .executeUpdate();
        if (isSuccess == 0) {
            throw new RuntimeException("Troubles with deleting.");
        }
    }

    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Card c").executeUpdate();
    }

    public CardAllInfoDto search(int page, CardQueryDto query) {
        final int recordsPerPage = 3;
        List<Card> cards = entityManager.createQuery("SELECT c from Card c WHERE " +
                " (:name IS NULL OR c.name=:name) AND (:surname IS NULL OR c.surname=:surname) AND" +
                " (:code IS NULL OR c.code=:code) AND (:cvv IS NULL OR c.cvv=:cvv) AND" +
                " (:date IS NULL OR c.creationDate=:date)", Card.class)
                .setParameter("name", query.getName())
                .setParameter("surname", query.getSurname())
                .setParameter("code", query.getCode())
                .setParameter("cvv", query.getCvv())
                .setParameter("date", query.getCreationDate())
                .setMaxResults(recordsPerPage)
                .setFirstResult(page * recordsPerPage)
                .getResultList();
        return new CardAllInfoDto(listCard2ListInfo(cards));
    }

    private List<CardInfoDto> listCard2ListInfo(List<Card> cards) {
        List<CardInfoDto> dtos = new ArrayList<>();
        for (Card card : cards) {
            dtos.add(card2InfoDto(card));
        }
        return dtos;
    }

    private CardInfoDto card2InfoDto(Card card) {
        return CardInfoDto.builder()
                .cardId(card.getCardId())
                .name(card.getName())
                .surname(card.getSurname())
                .build();
    }

    protected CardDetailsDto card2CardDetails(Card card) {
        return CardDetailsDto.builder()
                .cardId(card.getCardId())
                .name(card.getName())
                .surname(card.getSurname())
                .code(card.getCode())
                .cvv(card.getCvv())
                .creationDate(card.getCreationDate())
                .build();
    }

    private List<CardDetailsDto> listCard2ListCardDetails(List<Card> cards) {
        List<CardDetailsDto> dtos = new ArrayList<>();
        for (Card card : cards) {
            dtos.add(card2CardDetails(card));
        }
        return dtos;
    }

}
